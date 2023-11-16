package com.mysite.sbb.domain.user.api;

import com.mysite.sbb.domain.user.application.UserService;
import com.mysite.sbb.domain.user.dto.AddUserRequest;
import com.mysite.sbb.domain.user.dto.AddUserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String add(AddUserRequest addUserRequest) {
        return "signup_form";
    }

    @PostMapping
    public String add(
            @Valid AddUserRequest addUserRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!addUserRequest.getPassword().equals(addUserRequest.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        // 회원가입 예외처리 필요.

        AddUserResponse result = userService.add(addUserRequest);

        return "redirect:/";
    }
}
