package com.mysite.sbb.domain.question.dao;

import com.mysite.sbb.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findBySubject(String subject);
    Optional<Question> findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectOrContent(String subject, String content);
    List<Question> findByCreateDateBetween(LocalDateTime from, LocalDateTime to);
    List<Question> findBySubjectLike(String subject);
    List<Question> findBySubjectIn(String[] subjects);
    List<Question> findBySubjectOrderByCreateDateAsc(String subject);
}
