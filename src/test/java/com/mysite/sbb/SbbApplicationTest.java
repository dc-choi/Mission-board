package com.mysite.sbb;

import com.mysite.sbb.domain.answer.Answer;
import com.mysite.sbb.domain.answer.AnswerRepository;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.question.dao.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SbbApplicationTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    void testFindAll() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);

        List<Question> questions = this.questionRepository.findAll();
        assertThat(questions.size()).isEqualTo(2);
    }

    @Test
    void testFindById() {
        Question question = new Question();
        question.setSubject("그렇군요!");
        question.setContent("앞으로 더 알아가요!");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        Optional<Question> optionalQuestion = this.questionRepository.findById(question.getId());
        assertThat(optionalQuestion.isPresent()).isTrue();
        assertThat(optionalQuestion.get().getId()).isEqualTo(question.getId());
    }
    
    @Test
    void testFindBySubject() {
        Question question = new Question();
        question.setSubject("네네!");
        question.setContent("선생님!");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        Optional<Question> optionalQuestion = this.questionRepository.findBySubject(question.getSubject());
        assertThat(optionalQuestion.isPresent()).isTrue();
        assertThat(optionalQuestion.get().getId()).isEqualTo(question.getId());
    }

    @Test
    void testFindBySubjectAndContent() {
        Question question = new Question();
        question.setSubject("test");
        question.setContent("test");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        Optional<Question> optionalQuestion = this.questionRepository.findBySubjectAndContent(question.getSubject(), question.getContent());
        assertThat(optionalQuestion.isPresent()).isTrue();
        assertThat(optionalQuestion.get().getId()).isEqualTo(question.getId());
    }

    @Test
    void testFindBySubjectOrContent() {
        Question question = new Question();
        question.setSubject("test3");
        question.setContent("test3");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        List<Question> questionList = this.questionRepository.findBySubjectOrContent(question.getSubject(), question.getContent());
        Question question1 = questionList.get(0);
        assertThat(question1.getId()).isEqualTo(question.getId());
    }

    @Test
    void testFindBySubjectLike() {
        Question question = new Question();
        question.setSubject("test2");
        question.setContent("test2");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        List<Question> questionList = this.questionRepository.findBySubjectLike("%test2%");
        Question question1 = questionList.get(0);
        assertThat(question1.getId()).isEqualTo(question.getId());
    }

    @Test
    void testFindBySubjectIn() {
        Question q1 = new Question();
        q1.setSubject("11");
        q1.setContent("11");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("22");
        q2.setContent("22");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);

        List<Question> questions1 = this.questionRepository.findBySubjectIn(new String[]{"11", "22"});
        assertThat(questions1.size()).isEqualTo(2);
    }

    @Test
    void testFindBySubjectOrderByCreateDateAsc() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        List<Question> questions1 = this.questionRepository.findBySubjectOrderByCreateDateAsc(q1.getSubject());
        assertThat(questions1.get(0).getSubject()).isEqualTo(q1.getSubject());
    }

    @Test
    void testUpdate() {
        Question question = new Question();
        question.setSubject("qwer");
        question.setContent("qwer");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        Optional<Question> optionalQuestion = this.questionRepository.findById(1);
        assertThat(optionalQuestion.isPresent()).isTrue();

        Question question1 = optionalQuestion.get();
        question1.setSubject("asdf");
        this.questionRepository.save(question1);

        assertThat(question1.getId()).isEqualTo(1);
    }

    @Test
    void testDelete() {
        Question question = new Question();
        question.setSubject("qwer");
        question.setContent("qwer");
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question);

        this.questionRepository.delete(question);
    }

    @Test
    @Transactional
    void testAnswer() {
        Question initQuestion = new Question();
        initQuestion.setSubject("qwer");
        initQuestion.setContent("qwer");
        initQuestion.setCreateDate(LocalDateTime.now());

        List<Answer> answers = new ArrayList<>();

        Answer initAnswer = new Answer();
        initAnswer.setQuestion(initQuestion);
        initAnswer.setContent("아니 그게 말이야 빙구야");
        initQuestion.setAnswers(answers);

        answers.add(initAnswer);

        this.questionRepository.save(initQuestion);
        this.answerRepository.save(initAnswer);

        Optional<Question> optionalQuestion = this.questionRepository.findById(1);
        Optional<Answer> optionalAnswer = this.answerRepository.findById(1);

        assertThat(optionalQuestion.isPresent()).isTrue();
        assertThat(optionalAnswer.isPresent()).isTrue();

        Question question = optionalQuestion.get();
        List<Answer> answerList = question.getAnswers();
        Answer answer = optionalAnswer.get();

        assertThat(answerList).isNotEmpty();

        // Lazy 로딩이 되어서 @Transactional 어노테이션으로 DB 세션을 유지시킨다.
        assertThat(question.getAnswers().get(0)).isEqualTo(answer);
    }
}