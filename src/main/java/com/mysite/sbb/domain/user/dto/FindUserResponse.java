package com.mysite.sbb.domain.user.dto;

import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.user.entity.SiteUser;
import jakarta.persistence.Column;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FindUserResponse {
    private Long id;
    private String username;
    private String password;
    private String email;

    public static FindUserResponse of(SiteUser siteUser) {
        return FindUserResponse.builder()
                .id(siteUser.getId())
                .username(siteUser.getUsername())
                .email(siteUser.getEmail())
                .build();
    }
}
