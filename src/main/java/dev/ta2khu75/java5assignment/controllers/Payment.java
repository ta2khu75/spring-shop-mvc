package dev.ta2khu75.java5assignment.controllers;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.Status;
import dev.ta2khu75.java5assignment.payment.PaymentDTO;
import dev.ta2khu75.java5assignment.payment.PaymentService;
import dev.ta2khu75.java5assignment.services.OrderService;

import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class Payment {
    private final PaymentService service;
    private final OrderService orderService;
    @GetMapping("/payment/{orderId}")
    public String pay(HttpServletRequest request, @PathVariable Long orderId) {
        PaymentDTO.VNPayResponse pab=service.createVnPayPayment(request, orderId);
        return String.format("redirect:%s", pab.paymentUrl);
    }
    @GetMapping("/payment-result/{orderId}")
    public String payCallbackHandler(HttpServletRequest request, @PathVariable Long orderId) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            Order order=orderService.getOrderById(orderId);
            order.setStatus(Status.PROCESSING);
            orderService.updateOrder(order);
            return "redirect:/success";
        } else {
            return  "redirect:/failed?message=buyFailed";
        }
    }
    @GetMapping("success")
    public String getMethodName(Model model) {
        model.addAttribute("page", "success");
        return "index";
    }
    @GetMapping("failed")
    public String getMethodName(Model model,@RequestParam("message") String message) {
        model.addAttribute("message", message);
        model.addAttribute("page", "error");
        return "index";
    }
    
    
}
