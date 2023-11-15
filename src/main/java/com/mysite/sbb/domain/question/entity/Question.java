package com.mysite.sbb.domain.question.entity;

import com.mysite.sbb.domain.answer.entity.Answer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public Question() {
    }

    @Builder
    public Question(Integer id, String subject, String content, LocalDateTime createDate, List<Answer> answers) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.createDate = createDate;
        this.answers = answers;
    }
}
