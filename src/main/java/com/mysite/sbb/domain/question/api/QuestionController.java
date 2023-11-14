package com.mysite.sbb.domain.question.api;

import com.mysite.sbb.domain.question.application.QuestionService;
import com.mysite.sbb.domain.question.dto.AddQuestionRequest;
import com.mysite.sbb.domain.question.dto.AddQuestionResponse;
import com.mysite.sbb.domain.question.dto.FindQuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/page/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<FindQuestionResponse> result = this.questionService.list();
        model.addAttribute("list", result);
        return "question_list";
    }

    @GetMapping("/{id}/detail")
    public String get(Model model, @PathVariable("id") Integer id) {
        FindQuestionResponse result = this.questionService.get(id);
        model.addAttribute("detail", result);
        return "question_detail";
    }

    @GetMapping("/add")
    public String add() {
        return "question_add";
    }

    /**
     * 결론적으로 JSON을 주고받지 않으면 RequestParam을 사용해야 함.
     * https://way-be-developer.tistory.com/242
     *
     * @param model
     * @param subject
     * @param content
     * @return
     */
    @PostMapping("/add")
    public String add(
            Model model,
            @RequestParam(value = "subject") String subject,
            @RequestParam(value = "content") String content
    ) {
        AddQuestionRequest request = AddQuestionRequest.builder()
                .subject(subject)
                .content(content)
                .build();

        AddQuestionResponse result = this.questionService.add(request);
        return "redirect:/page/question/list";
    }
}
