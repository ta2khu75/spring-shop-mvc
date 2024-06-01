package dev.ta2khu75.java5assignment.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dev.ta2khu75.java5assignment.mappers.UserMapper;
import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.Status;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.OrderService;
import dev.ta2khu75.java5assignment.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class Profile {
    private final OrderService service;
    private final UserService userService;
    private final HttpSession session;
    private final UserMapper mapper;

    @GetMapping("")
    public String getMethodName() {
        return "index";
    }

    @PostMapping("")
    public String postMethodName(@ModelAttribute("User") UserResp userResp) {
        User user = userService.updateUserResp(userResp);
        session.setAttribute("user", mapper.toUserResp(user));
        return "redirect:/profile";
    }

    @ModelAttribute("user")
    public UserResp getUser(@SessionAttribute("user") UserResp user) {
        return user;
    }

    @ModelAttribute("order")
    public Order getOrder(@SessionAttribute("user") UserResp user, Model model) {
        Order order = service.findTopByUserIdOrderByIdDesc(user.getId());
        if (order != null) {
            Status status = order.getStatus();
            if (status.equals(Status.PENDING) || status.equals(Status.PROCESSING)) {
                model.addAttribute("cancel", true);
            }
        }
        return order;
    }

    @ModelAttribute("newOrders")
    public List<Order> getNewOrders(@SessionAttribute("user") UserResp user) {
        return service.getNewOrder(user.getId());
    }

    @ModelAttribute("oldOrders")
    public List<Order> getOldOrders(@SessionAttribute("user") UserResp user) {
        return service.getOldOrder(user.getId());
    }

    @ModelAttribute("cancelOrder")
    public List<Order> getCancelOrders(@SessionAttribute("user") UserResp user) {
        return service.getOrderByUserIdAndStatus(user.getId(), Status.CANCELED);
    }

    @ModelAttribute("completeOrder")
    public List<Order> getCompleteOrders(@SessionAttribute("user") UserResp user) {
        return service.getOrderByUserIdAndStatus(user.getId(), Status.DELIVERED);
    }

    @PostMapping("change-password")
    public String postMethodName(@SessionAttribute("user") UserResp userResp, @RequestParam String oldPassword,
            @RequestParam String password, @RequestParam String confirmPassword) {
        if (oldPassword.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return "redirect:/profile?error=true";
        }
        User user = userService.getUserById(userResp.getId());
        if (user != null && user.getPassword().equals(oldPassword) && password.equals(confirmPassword)) {
            user.setPassword(BCrypt.withDefaults().hashToString(12, password.toCharArray()));
            userService.updateUser(user);
            session.removeAttribute("user");
        }
        return "redirect:/profile";
    }

    @ModelAttribute("page")
    public String getPage() {
        return "profile";
    }
}
