package dev.ta2khu75.java5assignment.controllers;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductDetails {
    private final ProductService service;
    @RequestMapping("product-details/{id}")
    public String requestMethodName(@PathVariable Long id, Model model) throws IOException, ClassNotFoundException {
        model.addAttribute("product", service.getProductById(id));
        return "index";
    }
    @ModelAttribute("page")
    public String getPage(){
        return "product-details";
    }
}
