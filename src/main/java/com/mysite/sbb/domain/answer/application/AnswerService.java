package com.mysite.sbb.domain.answer.application;

import com.mysite.sbb.domain.answer.dao.AnswerRepository;
import com.mysite.sbb.domain.answer.dto.AddAnswerRequest;
import com.mysite.sbb.domain.answer.dto.AddAnswerResponse;
import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.question.dao.QuestionRepository;
import com.mysite.sbb.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public AddAnswerResponse add(AddAnswerRequest request) {
        Question question = this.questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 질문입니다."));

        Answer answer = AddAnswerRequest.toEntity(request, question);

        return AddAnswerResponse.of(this.answerRepository.save(answer));
    }
}
