package dev.ta2khu75.java5assignment.controllers;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.resps.UserResp;
import dev.ta2khu75.java5assignment.services.CartService;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("cart")
@RequiredArgsConstructor
public class Cart {
    private final CartService service;
    private final ProductService productService;

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

    @ModelAttribute("carts")
    public Map<Product, Integer> getCarts(@SessionAttribute("user") UserResp user) throws JsonProcessingException, ClassNotFoundException {
        Map<Long, Integer> map = service.getCart(user.getId());
        Map<Product, Integer> cart = new LinkedHashMap<>();
        if (map == null) {
            return null;
        }
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            Product product = productService.getProductById(entry.getKey());
            cart.put(product, entry.getValue());
        }
        return cart;
    }

    @GetMapping
    public String requestMethodName() {
        return "index";
    }

    @GetMapping("increment/{id}")
    public String getMethodName(@SessionAttribute("user") UserResp user, @PathVariable("id") Long id) {
        service.incrementCart(user.getId(), id);
        return "redirect:/cart";
    }

    @GetMapping("decrement/{id}")
    public String getMethodName(@PathVariable("id") Long id, @SessionAttribute UserResp user) {
        service.decrementCart(user.getId(), id);
        return "redirect:/cart";
    }

    @GetMapping("add/{id}")
    public String requestMethodName(@SessionAttribute("user") UserResp user, @PathVariable Long id,
            @RequestParam Integer quantity) {
        service.addToCart(user.getId(), id, quantity);
        return "redirect:/product-details/" + id;
    }

    @RequestMapping("remove/{id}")
    public String requestMethodName(@SessionAttribute("user") UserResp user, @PathVariable Long id) {
        service.deleteFromCart(user.getId(), id);
        return "redirect:/cart";
    }
    @ModelAttribute("page")
    public String getPage(){
        return "cart";
    }

}
