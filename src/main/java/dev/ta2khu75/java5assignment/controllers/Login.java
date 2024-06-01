package dev.ta2khu75.java5assignment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import dev.ta2khu75.java5assignment.dtoes.UserLogin;
import dev.ta2khu75.java5assignment.mappers.UserMapper;
import dev.ta2khu75.java5assignment.models.Role;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String getMethodName(@ModelAttribute("user") UserLogin user, Model model, HttpServletRequest req,
            HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        String email = "";
        String password = "";
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("email")) {
                    email = c.getValue();
                } else if (c.getName().equals("password")) {
                    password = c.getValue();
                }
            }
        }
        System.out.println("email: " + email + ", password: " + password);
        if (email.isBlank() || password.isBlank()) {
            refreshCaptcha(session, model);
            return "index";
        } else {
            return login(email, password, true, resp, session, model);
        }
    }

    @ModelAttribute("page")
    public String getPage(@RequestParam(required = false) boolean errorValid,
            @RequestParam(required = false) String error,
            @RequestParam(required = false) boolean errorLogin, Model model) {
        model.addAttribute("errorValid", errorValid);
        model.addAttribute("errorLogin", errorLogin);
        model.addAttribute("error", error);
        return "login";
    }

    @PostMapping
    public String postMethodName(Model model,
            @Valid @ModelAttribute("user") UserLogin user,
            BindingResult bindingResult, @SessionAttribute("codeCaptchaLogin") String code,
            @SessionAttribute("imageCaptchaLogin") String image,
            @RequestParam String captcha, @RequestParam(required = false) boolean remember,
            HttpServletResponse response) {
        if (!bindingResult.hasErrors()) {
            if (captcha.equals(code)) {
                return login(user.getEmail(), user.getPassword(), remember, response, session, model);
            } else {
                model.addAttribute("errorCaptcha", true);
            }
        } else {
            model.addAttribute("errorValid", true);
        }
        model.addAttribute("captcha", image);
        return "index";
    }

    private void refreshCaptcha(HttpSession session, Model model) {
        GifCaptcha errorLoginCaptcha = CaptchaUtil.createGifCaptcha(150, 50);
        session.setAttribute("codeCaptchaLogin", errorLoginCaptcha.getCode());
        session.setAttribute("imageCaptchaLogin", errorLoginCaptcha.getImageBase64Data());
        model.addAttribute("captcha", errorLoginCaptcha.getImageBase64Data());
    }

    private String login(String emailString, String passwordString, boolean remember, HttpServletResponse resp,
            HttpSession session, Model model) {
        User result = service.login(emailString, passwordString);
        if (result != null) {
            if (result.isLocked()) {
                model.addAttribute("errorLocked", true);
                return "index";
            }
            Cookie email = new Cookie("email", emailString);
            Cookie password = new Cookie("password", passwordString);
            email.setMaxAge(30 * 24 * 60 * 60);
            password.setMaxAge(30 * 24 * 60 * 60);
            if (!remember) {
                email.setMaxAge(0);
                password.setMaxAge(0);
            }
            resp.addCookie(email);
            resp.addCookie(password);
            session.setAttribute("user", mapper.toUserResp(result));
            if (result.getRole().equals(Role.ADMIN)) {
                return "redirect:/admin";
            }
            return "redirect:/";
        }
        return "redirect:/logout?errorLogin="+true;
    }

}
