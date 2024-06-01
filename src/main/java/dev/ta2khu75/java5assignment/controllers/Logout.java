package dev.ta2khu75.java5assignment.controllers;

import org.springframework.stereotype.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class Logout {
    private final HttpSession session;

    @GetMapping("logout")
    public String getMethodName(HttpServletRequest res, HttpServletResponse resp, @RequestParam(required = false) boolean errorLogin) {
        Cookie[] cookies = res.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("email") || cookie.getName().equals("password")) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }

        }
        session.removeAttribute("user");
        return "redirect:/login?errorLogin=" + errorLogin;
    }
}
