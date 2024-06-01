package dev.ta2khu75.java5assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.hutool.captcha.CircleCaptcha;
import dev.ta2khu75.java5assignment.dtoes.UserDto;
import dev.ta2khu75.java5assignment.exceptions.AlreadyExistsException;
import dev.ta2khu75.java5assignment.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("register")
@RequiredArgsConstructor
public class Register {
    @NonFinal
    public static final String MESSAGE = "message";
    private final UserService service;
    private final HttpSession session;

    @GetMapping
    public String requestMethodName(@ModelAttribute("user") UserDto userDto, Model model) {
        CircleCaptcha captcha = new CircleCaptcha(100, 35, 4, 20);
        session.setAttribute("codeCaptchaRegister", captcha.getCode());
        session.setAttribute("imageCaptchaRegister", captcha.getImageBase64Data());
        model.addAttribute("captcha", captcha.getImageBase64Data());
        return "index";
    }

    @ModelAttribute("page")
    public String getPage(@RequestParam(required = false) boolean errorValid,
            @RequestParam(required = false) boolean errorEmail, @RequestParam(required = false) boolean errorPassword,
            @RequestParam(required = false) boolean success, Model model) {
        model.addAttribute("errorValid", errorValid);
        model.addAttribute("errorEmail", errorEmail);
        model.addAttribute("errorPassword", errorPassword);
        model.addAttribute("success", success);
        return "register";
    }

    @PostMapping
    public String postMethodName(Model model, @RequestParam String captcha,
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                model.addAttribute("errorPassword", true);
                return "index";
            } else if (session.getAttribute("codeCaptchaRegister").equals(captcha)) {
                try {
                    service.createUser(userDto);
                    return "redirect:/register?success=true";
                } catch (AlreadyExistsException e) {
                    model.addAttribute("errorEmail", true);
                    CircleCaptcha errorEmailCaptcha = new CircleCaptcha(100, 35, 4, 20);
                    model.addAttribute("captcha", errorEmailCaptcha.getImageBase64Data());
                    session.setAttribute("codeCaptchaRegister", errorEmailCaptcha.getCode());
                    session.setAttribute("imageCaptchaRegister", errorEmailCaptcha.getCode());
                    return "index";
                }
            } else {
                model.addAttribute("errorCaptcha", true);
            }
        } else {
            model.addAttribute("errorValid", true);
        }
        model.addAttribute("captcha", session.getAttribute("imageCaptchaRegister"));
        return "index";
    }

}