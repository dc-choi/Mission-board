package com.mysite.sbb.domain.answer.dto;

import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.question.entity.Question;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddAnswerRequest {
    private Integer questionId;
    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    public static Answer toEntity(AddAnswerRequest request, Question question) {
        return Answer.builder()
                .question(question)
                .content(request.getContent())
                .build();
    }
}
