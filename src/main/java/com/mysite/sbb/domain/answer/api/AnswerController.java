package com.mysite.sbb.domain.answer.api;

import com.mysite.sbb.domain.answer.application.AnswerService;
import com.mysite.sbb.domain.answer.dto.AddAnswerRequest;
import com.mysite.sbb.domain.answer.dto.AddAnswerResponse;
import com.mysite.sbb.domain.question.application.QuestionService;
import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/page/answer")
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping
    public String add(
            Model model,
            @Valid AddAnswerRequest addAnswerRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            FindQuestionResponse findQuestionResponse = this.questionService.get(addAnswerRequest.getQuestionId());
            model.addAttribute("detail", findQuestionResponse);
            return "question_detail";
        }

        AddAnswerResponse result = this.answerService.add(addAnswerRequest);

        StringBuilder stringBuilder = new StringBuilder("redirect:/page/question/");
        stringBuilder.append(addAnswerRequest.getQuestionId());
        stringBuilder.append("/detail");

        return stringBuilder.toString();
    }
}
