package com.mysite.sbb.domain.question.dto;

import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.question.entity.Question;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FindQuestionResponse {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private List<Answer> answers;

    public static FindQuestionResponse of(Question question) {
        return FindQuestionResponse.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .content(question.getContent())
                .createDate(question.getCreateDate())
                .answers(question.getAnswers())
                .build();
    }

    public static Page<FindQuestionResponse> of(Page<Question> questions) {
        List<FindQuestionResponse> list = questions.stream()
                .map(FindQuestionResponse::of)
                .toList();

        Pageable pageable = PageRequest.of(questions.getNumber(), questions.getSize());

        // Page의 구현체를 직접 반환해준다.
        return new PageImpl<>(list, pageable, list.size());
    }
}
