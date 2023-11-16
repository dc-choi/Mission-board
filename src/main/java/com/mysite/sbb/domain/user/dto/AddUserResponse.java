package com.mysite.sbb.domain.user.dto;

import com.mysite.sbb.domain.question.dto.AddQuestionResponse;
import com.mysite.sbb.domain.question.entity.Question;
import com.mysite.sbb.domain.user.entity.SiteUser;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class AddUserResponse {
    private Long id;
    private String username;
    private String email;

    public static AddUserResponse of(SiteUser siteUser) {
        return AddUserResponse.builder()
                .id(siteUser.getId())
                .username(siteUser.getUsername())
                .email(siteUser.getEmail())
                .build();
    }
}
