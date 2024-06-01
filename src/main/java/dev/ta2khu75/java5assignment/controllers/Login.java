package dev.ta2khu75.java5assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.ta2khu75.java5assignment.dtoes.UserLogin;
import dev.ta2khu75.java5assignment.mappers.UserMapper;
import dev.ta2khu75.java5assignment.models.Role;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("login")
@RequiredArgsConstructor
public class Login {
    private final HttpSession session;
    private final UserService service;
    private final UserMapper mapper;

    @GetMapping
    public String getMethodName(@ModelAttribute("user") UserLogin user) {
        return "index";
    }

    @ModelAttribute("page")
    public String getPage(@RequestParam(required = false) boolean errorValid,@RequestParam(required = false) String error,
            @RequestParam(required = false) boolean errorLogin, Model model) {
        model.addAttribute("errorValid", errorValid);
        model.addAttribute("errorLogin", errorLogin);
        model.addAttribute("error", error);
        System.out.println(error);
        return "login";
    }

    @PostMapping
    public String postMethodName(String sessionId, Model model,
            @Valid @ModelAttribute("user") UserLogin user,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            User result = service.login(user.getEmail(), user.getPassword());
            if (result != null) {
                if(result.isLocked()){
                    model.addAttribute("errorLocked", true);
                    return "index";
                }
                session.setAttribute("user", mapper.toUserResp(result));
                if (result.getRole().equals(Role.ADMIN)) {
                    return "redirect:/admin";
                }
                return "redirect:/";
            }
            model.addAttribute("errorLogin", true);
            return "index";
        }
        model.addAttribute("errorValid", true);
        return "index";
    }

}
