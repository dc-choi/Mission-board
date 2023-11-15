package com.mysite.sbb.domain.answer.api;

import com.mysite.sbb.domain.answer.application.AnswerService;
import com.mysite.sbb.domain.answer.dto.AddAnswerRequest;
import com.mysite.sbb.domain.answer.dto.AddAnswerResponse;
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

    @PostMapping
    public String add(
            Model model,
            @Valid AddAnswerRequest request,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) return "question_detail";

        AddAnswerResponse result = this.answerService.add(request);

        StringBuilder stringBuilder = new StringBuilder("redirect:/page/question/");
        stringBuilder.append(request.getQuestionId());
        stringBuilder.append("/detail");

        model.addAttribute("request", request);

        return stringBuilder.toString();
    }
}
