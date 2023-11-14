package com.mysite.sbb.domain.question.application;

import com.mysite.sbb.domain.question.dto.AddQuestionRequest;
import com.mysite.sbb.domain.question.dto.AddQuestionResponse;
import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import com.mysite.sbb.domain.question.dao.QuestionRepository;
import com.mysite.sbb.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<FindQuestionResponse> list() {
        return FindQuestionResponse.of(this.questionRepository.findAll());
    }

    public FindQuestionResponse get(Integer id) {
        return FindQuestionResponse.of(this.questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 질문입니다.")));
    }

    public AddQuestionResponse add(AddQuestionRequest request) {
        Question question = AddQuestionRequest.toEntity(request);
        return AddQuestionResponse.of(this.questionRepository.save(question));
    }
}
