package dev.ta2khu75.java5assignment.controllers.crud;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("crud/order")
@RequiredArgsConstructor
public class OrderCrud {
    private final OrderService service;
    private final HttpSession session;

    @PostMapping
    public String postMethodName(@ModelAttribute Order order) {
        Order orderExisting = service.getOrderById(order.getId());
        orderExisting.setStatus(order.getStatus());
        orderExisting.setPaymentMethod(order.getPaymentMethod());
        service.updateOrder(orderExisting);
        return "redirect:/crud/order";
    }

    @GetMapping
    public String getMethodName(@ModelAttribute Order order) {
        return "crud/order";
    }

    @ModelAttribute("orders")
    public List<Order> getOrders() {
        return service.getAllOrders();
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
        model.addAttribute("order", service.getOrderById(id));
        return "crud/order";
    }

    @GetMapping("delete")
    public String getMethodName(@RequestParam Long param) {
        service.deleteOrder(param);
        return "redirect:/crud/order/";
    }

    @ModelAttribute
    public void getAuthorization(@SessionAttribute("user") UserResp userResp) {
        if (userResp == null || !userResp.getRole().equals(Role.ADMIN)) {
            throw new UnAuthorizationException("Access denied");
        }
    }

}
