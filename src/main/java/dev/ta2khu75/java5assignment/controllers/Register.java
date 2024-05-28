package dev.ta2khu75.java5assignment.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.ta2khu75.java5assignment.dtoes.UserDto;
import dev.ta2khu75.java5assignment.services.UserService;
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

    @GetMapping
    public String requestMethodName(@ModelAttribute("user") UserDto userDto) {
        return "index";
    }

    @ModelAttribute("page")
    public String getPage() {
        return "register";
    }

    @PostMapping
    public String postMethodName(Model model, @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                model.addAttribute(MESSAGE, "Password not match confirm password");
                return "index";
            }
            try {
                service.createUser(userDto);
                model.addAttribute(MESSAGE, "Register success");
                return "index";
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                model.addAttribute(MESSAGE, "Email is existing");
            }
        }
        return "index";
    }

}