package dev.ta2khu75.java5assignment.controllers.crud;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import dev.ta2khu75.java5assignment.exceptions.UnAuthorizationException;
import dev.ta2khu75.java5assignment.mappers.UserMapper;
import dev.ta2khu75.java5assignment.models.Role;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("crud/user")
public class UserCrud {
    private List<UserResp> users;
    private final UserMapper mapper;
    private final UserService service;

    @GetMapping
    public String getMethodName(@ModelAttribute("user") UserResp user) {
        return "crud/admin";
    }

    @PostMapping
    public String postMethodName(Model models, @Valid @ModelAttribute("user") UserResp user,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                service.updateUserResp(user);
                models.addAttribute("message", "Thanh cong");
                return "redirect:/crud/user";
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
        models.addAttribute("message", "That bai");
        return "redirect:/crud/user";
    }

    @GetMapping("delete/{id}")
    public String deleteMethod(@PathVariable Long id, @ModelAttribute User user) {
        service.deleteUser(id);
        return "redirect:/crud/user";
    }

    @GetMapping("{id}")
    public String getMethodName(@PathVariable Long id, Model model) {
        User userExisting = service.getUserById(id);
        if (userExisting != null) {
            UserResp userResp = mapper.toUserResp(userExisting);
            model.addAttribute("user", userResp);
        }
        return "crud/admin";
    }

    @ModelAttribute("roles")
    public Role[] getRoles() {
        return Role.values();
    }

    @ModelAttribute("users")
    public List<UserResp> getUsers() {
        users = service.getAllUsers().stream().map(mapper::toUserResp).toList();
        return users;
    }

    @ModelAttribute
    public void getAuthorization(@SessionAttribute("user") UserResp userResp) {
        if (userResp == null || !userResp.getRole().equals(Role.ADMIN)) {
            throw new UnAuthorizationException("Access denied");
        }
    }
    @ModelAttribute("page")
    public String getPage() {
        return "user";
    }

}
