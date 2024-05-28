package dev.ta2khu75.java5assignment.controllers;

import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class Logout {
    private final HttpSession session;
    @GetMapping("logout")
    public String getMethodName() {
        session.removeAttribute("user");
        return "redirect:/login";
    }
}
