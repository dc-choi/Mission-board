package com.mysite.sbb.domain.question.api;

import com.mysite.sbb.domain.answer.dto.AddAnswerRequest;
import com.mysite.sbb.domain.question.application.QuestionService;
import com.mysite.sbb.domain.question.dto.AddQuestionRequest;
import com.mysite.sbb.domain.question.dto.AddQuestionResponse;
import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/page/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(
            Model model,
            // 스프링부트의 페이징은 첫페이지 번호가 1이 아닌 0이다.
            @RequestParam(value="page", defaultValue="0") int page
    ) {
        Page<FindQuestionResponse> result = this.questionService.list(page);
        model.addAttribute("list", result);
        return "question_list";
    }

    @GetMapping("/{id}/detail")
    public String get(
            Model model,
            @PathVariable("id") Integer id,
            AddAnswerRequest addAnswerRequest
    ) {
        FindQuestionResponse result = this.questionService.get(id);
        model.addAttribute("detail", result);
        return "question_detail";
    }

    // 로그인이 되어야지만 사용할 수 있다는 표시
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/add")
    public String add(
            Model model,
            AddQuestionRequest addQuestionRequest
    ) {
        return "question_add";
    }

    // 결론적으로 JSON을 주고받지 않으면 RequestParam을 사용해야 함.
    // https://way-be-developer.tistory.com/242
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public String add(
            Model model,
            @Valid AddQuestionRequest addQuestionRequest,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            return "question_add";
        }

        addQuestionRequest.setAuthor(principal.getName());
        AddQuestionResponse result = this.questionService.add(addQuestionRequest);
        return "redirect:/page/question/list";
    }
}
