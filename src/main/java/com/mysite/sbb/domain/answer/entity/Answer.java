package com.mysite.sbb.domain.answer.entity;

import com.mysite.sbb.domain.question.entity.Question;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    private Question question;

    public Answer() {
    }

    @Builder
    public Answer(Integer id, String content, LocalDateTime createDate, Question question) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.question = question;
    }
}