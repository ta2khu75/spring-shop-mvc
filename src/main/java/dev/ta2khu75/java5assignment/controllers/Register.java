package dev.ta2khu75.java5assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.ta2khu75.java5assignment.dtoes.UserDto;
import dev.ta2khu75.java5assignment.exceptions.AlreadyExistsException;
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
    public String postMethodName(Model model, @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
                model.addAttribute("errorPassword", true);
                return "index";
            }
            try {
                service.createUser(userDto);
                return "redirect:/register?success=true";
            } catch (AlreadyExistsException e) {
                model.addAttribute("errorEmail", true);
                return "index";
            }
        }
        model.addAttribute("errorValid", true);
        return "index";
    }

}