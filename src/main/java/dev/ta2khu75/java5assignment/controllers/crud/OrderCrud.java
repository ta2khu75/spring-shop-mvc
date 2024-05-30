package dev.ta2khu75.java5assignment.controllers.crud;

import java.util.Optional;
    
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.ta2khu75.java5assignment.exceptions.UnAuthorizationException;
import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.PaymentMethod;
import dev.ta2khu75.java5assignment.models.Role;
import dev.ta2khu75.java5assignment.models.Status;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("crud/order")
@RequiredArgsConstructor
public class OrderCrud {
    private final OrderService service;
    private final int SIZE = 10;

    @PostMapping
    public String postMethodName(@Validated @ModelAttribute Order order, BindingResult bindingResult, Model model,
            @RequestParam int pages) {
        if (!bindingResult.hasErrors()) {
            Order orderExisting = service.getOrderById(order.getId());
            orderExisting.setStatus(order.getStatus());
            orderExisting.setPaymentMethod(order.getPaymentMethod());
            service.updateOrder(orderExisting);
            return "redirect:/crud/order?successUpdate=true&pages=" + pages;
        }
        model.addAttribute("errorValid", true);
        model.addAttribute("pages", pages);
        return "crud/admin";
    }

    @GetMapping("view")
    public String getMethodNme(@RequestParam Long id, Model model) {
        Order order = service.getOrderById(id);
        model.addAttribute("order", order);
        model.addAttribute("page", "order-details");
        return "crud/admin";
    }

    @GetMapping
    public String getMethodName(@ModelAttribute Order order, @RequestParam(required = false) boolean errorDelete,
            @RequestParam(required = false) boolean successUpdate,
            Model model) {
        model.addAttribute("successUpdate", successUpdate);
        model.addAttribute("errorDelete", errorDelete);
        return "crud/admin";
    }

    @ModelAttribute("orders")
    public Page<Order> getOrders(@RequestParam(required = false) Optional<Integer> pages, Model model) {
        model.addAttribute("pages", pages.orElse(0));
        Pageable pageable = PageRequest.of(pages.orElse(0), SIZE);
        return service.getAllOrders(pageable);
    }

    @ModelAttribute("paymentMethods")
    public PaymentMethod[] getPaymentMethods() {
        return PaymentMethod.values();
    }

    @ModelAttribute("statuses")
    public Status[] geStatuses() {
        return Status.values();
    }

    @GetMapping("edit")
    public String getMethodName(@RequestParam Long id, Model model) {
        try {
            model.addAttribute("order", service.getOrderById(id));
        } catch (Exception e) {
            model.addAttribute("errorNotExist", true);
            model.addAttribute("order", new Order());
        }
        return "crud/admin";
    }

    @GetMapping("delete")
    public String getMethodName(@RequestParam Long id, @RequestParam(required = false) int pages) {
        try {
            service.deleteOrder(id);
            return "redirect:/crud/order?successDelete=true&pages=" + pages;
        } catch (Exception e) {
            return "redirect:/crud/order?errorDelete=true&pages=" + pages;
        }
    }

    @ModelAttribute
    public void getAuthorization(@SessionAttribute("user") UserResp userResp) {
        if (userResp == null || !userResp.getRole().equals(Role.ADMIN)) {
            throw new UnAuthorizationException("Access denied");
        }
    }

    @ModelAttribute("page")
    public String getPage() {
        return "order";
    }
}
