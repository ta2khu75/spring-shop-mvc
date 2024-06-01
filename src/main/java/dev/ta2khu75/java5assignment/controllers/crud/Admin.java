package dev.ta2khu75.java5assignment.controllers.crud;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.ta2khu75.java5assignment.services.OrderDetailsService;
import dev.ta2khu75.java5assignment.services.OrderService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Admin {
    private final OrderDetailsService orderDetailsService;
    private final OrderService service;
    private final ObjectMapper objectMapper;

    @GetMapping("admin")
    public String getMethodName(Model model) {
        model.addAttribute("page", "dashboard");
        return "crud/admin";
    }

    @GetMapping("admin/traffic")
    public String getMethodNam(Model model) throws JsonProcessingException {
        List<Object[]> list = service.getTotalOrderGroupDay();
        model.addAttribute("data", objectMapper.writeValueAsString(list));
        model.addAttribute("page", "traffic");
        return "crud/admin";
    }

    @GetMapping("admin/seo")
    public String getMethodNae(Model model) throws JsonProcessingException {
        List<Object[]> countProduct =orderDetailsService.countProduct();
        model.addAttribute("product", objectMapper.writeValueAsString(countProduct));
        List<Object[]> sumCategory =orderDetailsService.sumPriceGroupByCateogry();
        model.addAttribute("category", objectMapper.writeValueAsString(sumCategory));
        model.addAttribute("page", "seo");
        return "crud/admin";
    }
}
