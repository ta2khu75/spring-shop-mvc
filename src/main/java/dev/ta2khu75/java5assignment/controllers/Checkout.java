package dev.ta2khu75.java5assignment.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.models.PaymentMethod;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.CartService;
import dev.ta2khu75.java5assignment.services.OrderDetailsService;
import dev.ta2khu75.java5assignment.services.OrderService;
import dev.ta2khu75.java5assignment.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class Checkout {
    private final ProductService productService;
    private final CartService service;
    private final OrderService orderService;
    private final OrderDetailsService orderDetailsService;

    @RequestMapping("checkout")
    public String requestMethodName(@SessionAttribute("user") UserResp user, @ModelAttribute Order order, Model model) throws JsonProcessingException, ClassNotFoundException {
        Map<Long, Integer> map = service.getCart(user.getId());
        if (map == null) {
            return "redirect:/";
        }
        Map<Product, Integer> cart = new LinkedHashMap<>();
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            cart.put(productService.getProductById(entry.getKey()), entry.getValue());
        }
        model.addAttribute("carts", cart);
        return "index";
    }

    @ModelAttribute("total")
    public long getTotal(@SessionAttribute("user") UserResp user) throws JsonProcessingException, ClassNotFoundException {
        long total = 0;
        Map<Long, Integer> map = service.getCart(user.getId());
        if (map == null) {
            return 0;
        }
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            total += productService.getProductById(entry.getKey()).getPrice() * entry.getValue();
        }
        return total;
    }

    @PostMapping("order")
    public String postMethodName(@SessionAttribute("user") UserResp user, Model model, @Valid @ModelAttribute Order order,
            BindingResult bindingResult) throws IOException, ClassNotFoundException {
        if (bindingResult.hasErrors()) {
            return "checkout";
        }
        long amount = 0;
        order = orderService.createOrder(user.getId(), order);
        Map<Long, Integer> map = service.getCart(user.getId());
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            OrderDetails orderDetails = orderDetailsService.createOrderDetails(order, entry.getKey(), entry.getValue());
            Product product = productService.getProductById(entry.getKey());
            product.setQuantity(product.getQuantity() - orderDetails.getQuantity());
            if(product.getQuantity()<=0){
                product.setActive(false);
            }
            productService.updateProduct(product, null);
            amount += orderDetails.getProduct().getPrice() * orderDetails.getQuantity();
        }
        order.setTotal(amount);
        orderService.updateOrder(order);
        service.clearCart(user.getId());
        if (order.getPaymentMethod().equals(PaymentMethod.COD)) {
            model.addAttribute("page", "success");
            return "index";
        } else {
            return String.format("redirect:/payment/%d?amount=%d", order.getId(), amount);
        }
    }
    @ModelAttribute("page")
    public String page() {
        return "checkout";
    }
}
