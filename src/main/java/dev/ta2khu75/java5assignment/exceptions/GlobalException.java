package dev.ta2khu75.java5assignment.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(ServletRequestBindingException.class)
    public String handleException(ServletRequestBindingException e) {
        e.printStackTrace();
        return "redirect:/login";
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleException(NotFoundException e, Model model) {
        model.addAttribute("page", "404");
        return "index";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleException(NoResourceFoundException e, Model model) {
        model.addAttribute("page", "404");
        return "index";
    }
}
