package com.mysite.sbb.domain.question.dto;

import com.mysite.sbb.domain.question.entity.Question;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddQuestionRequest {
    private String subject;
    private String content;

    public static Question toEntity(AddQuestionRequest request) {
        return Question.builder()
                .subject(request.getSubject())
                .content(request.getContent())
                .build();
    }
}
