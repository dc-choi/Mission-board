package com.mysite.sbb.domain.answer.dto;

import com.mysite.sbb.domain.answer.entity.Answer;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddAnswerResponse {
    private Integer id;
    private String content;
    private LocalDateTime createDate;

    public static AddAnswerResponse of(Answer answer) {
        return AddAnswerResponse.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .createDate(answer.getCreateDate())
                .build();
    }
}
