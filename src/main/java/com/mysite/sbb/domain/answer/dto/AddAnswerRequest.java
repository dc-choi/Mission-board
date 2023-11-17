package com.mysite.sbb.domain.answer.dto;

import com.mysite.sbb.domain.answer.entity.Answer;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.user.entity.SiteUser;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddAnswerRequest {
    private Integer questionId;
    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;
    @Setter
    private String author;

    public static Answer toEntity(AddAnswerRequest request, Question question, SiteUser siteUser) {
        return Answer.builder()
                .question(question)
                .siteUser(siteUser)
                .content(request.getContent())
                .createDate(LocalDateTime.now())
                .build();
    }
}
