package com.mysite.sbb.domain.question.dto;

import com.mysite.sbb.domain.question.entity.Question;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddQuestionResponse {
    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;

    public static AddQuestionResponse of(Question question) {
        return AddQuestionResponse.builder()
                .id(question.getId())
                .subject(question.getSubject())
                .content(question.getContent())
                .createDate(question.getCreateDate())
                .build();
    }
}
