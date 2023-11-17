package com.mysite.sbb.domain.question.dto;

import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.user.entity.SiteUser;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddQuestionRequest {
    @NotEmpty(message = "제목은 필수 항목입니다.")
    @Size(max=200)
    private String subject;
    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;
    @Setter
    private String author;

    public static Question toEntity(AddQuestionRequest request, SiteUser siteUser) {
        return Question.builder()
                .siteUser(siteUser)
                .subject(request.getSubject())
                .content(request.getContent())
                .createDate(LocalDateTime.now())
                .build();
    }
}
