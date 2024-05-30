package dev.ta2khu75.java5assignment.controllers.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final UserMapper mapper;
    private final UserService service;
    private final int SIZE = 1;

    @GetMapping
    public String getMethodName(@ModelAttribute("user") UserResp user,
            @RequestParam(required = false) boolean errorNotExist,
            @RequestParam(required = false) boolean errorDelete,
            @RequestParam(required = false) boolean successUpdate,
            @RequestParam(required = false) boolean successDelete, Model model) {
        model.addAttribute("errorDelete", errorDelete);
        model.addAttribute("errorNotExist", errorNotExist);
        model.addAttribute("successUpdate", successUpdate);
        model.addAttribute("successDelete", successDelete);
        return "crud/admin";
    }

    @PostMapping
    public String postMethodName(Model models, @RequestParam int pages, @Valid @ModelAttribute("user") UserResp user,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                service.updateUserResp(user);
                return "redirect:/crud/user?successUpdate=true&pages=" + pages;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        models.addAttribute("pages", pages);
        models.addAttribute("errorValid", true);
        return "crud/admin";
    }

    @GetMapping("delete")
    public String deleteMethod(@RequestParam Long id, @RequestParam int pages) {
        try {
            service.deleteUser(id);
            return "redirect:/crud/user?successDelete=true&pages="+pages;
        } catch (Exception e) {
            return "redirect:/crud/user?errorDelete=true&pages="+pages;
        }
    }

    @GetMapping("{id}")
    public String getMethodName(@PathVariable Long id, Model model, @RequestParam int pages) {
        User userExisting = service.getUserById(id);
        if (userExisting != null) {
            UserResp userResp = mapper.toUserResp(userExisting);
            model.addAttribute("user", userResp);
        } else {
            model.addAttribute("user", new UserResp());
            model.addAttribute("errorNotExist", true);
        }
        model.addAttribute("pages", pages);
        return "crud/admin";
    }

    @ModelAttribute("roles")
    public Role[] getRoles() {
        return Role.values();
    }

    @ModelAttribute("users")
    public Page<UserResp> getUsers(@RequestParam(required = false, defaultValue = "0") int pages, Model model) {
        Pageable pageable = PageRequest.of(pages, SIZE);

        // Fetch the Page of User entities
        Page<User> userPage = service.getAllUsers(pageable);

        // Map the User entities to UserResp DTOs and collect to a List
        List<UserResp> userRespList = userPage.getContent().stream()
                .map(mapper::toUserResp)
                .collect(Collectors.toList());

        // Create a new PageImpl of UserResp
        Page<UserResp> userRespPage = new PageImpl<>(userRespList, pageable, userPage.getTotalElements());
        model.addAttribute("pages",pages);
        return userRespPage;
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
