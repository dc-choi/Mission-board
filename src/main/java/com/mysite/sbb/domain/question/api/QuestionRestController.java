package com.mysite.sbb.domain.question.api;

import com.mysite.sbb.domain.question.application.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/question")
public class QuestionRestController {
    private final QuestionService questionService;
}
