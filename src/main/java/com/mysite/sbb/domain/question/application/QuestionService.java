package com.mysite.sbb.domain.question.application;

import com.mysite.sbb.domain.question.dao.QuestionRepository;
import com.mysite.sbb.domain.question.dto.AddQuestionRequest;
import com.mysite.sbb.domain.question.dto.AddQuestionResponse;
import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.user.dao.UserRepository;
import com.mysite.sbb.domain.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

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
        SiteUser user = this.userRepository.findByUsername(request.getAuthor())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다."));

        Question question = AddQuestionRequest.toEntity(request, user);
        return AddQuestionResponse.of(this.questionRepository.save(question));
    }
}
