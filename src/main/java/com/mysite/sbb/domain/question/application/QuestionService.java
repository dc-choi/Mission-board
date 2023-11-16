package com.mysite.sbb.domain.question.application;

import com.mysite.sbb.domain.question.dao.QuestionRepository;
import com.mysite.sbb.domain.question.dto.AddQuestionRequest;
import com.mysite.sbb.domain.question.dto.AddQuestionResponse;
import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import com.mysite.sbb.domain.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Page<FindQuestionResponse> list(int page) {
        // 이 코드는 내림차순 정렬을 사용할 때 사용하는 로직이다.
        // List<Sort.Order> sorts = new ArrayList<>();
        // sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10);
        return FindQuestionResponse.of(this.questionRepository.findAll(pageable));
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
