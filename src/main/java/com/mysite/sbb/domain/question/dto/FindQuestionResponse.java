package com.mysite.sbb.domain.question.dto;

import com.mysite.sbb.domain.answer.Answer;
import com.mysite.sbb.domain.question.entity.Question;
import lombok.*;

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

    public static List<FindQuestionResponse> of(List<Question> questions) {
        return questions.stream()
                .map(FindQuestionResponse::of)
                .collect(Collectors.toList());
    }
}
