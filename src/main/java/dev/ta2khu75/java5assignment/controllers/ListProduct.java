package dev.ta2khu75.java5assignment.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/list-product")
@RequiredArgsConstructor
public class ListProduct {
    private final ProductService service;

    @GetMapping
    public String getMethodName() {
        return "index";
    }

    @PostMapping
    public String postMethodName(@RequestParam String keyword, Model model) {
        List<Product> products = service.getProductNameByKeyword(keyword);
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("{category}")
    public String getMethodName(@PathVariable Category category, Model model) {
        List<Product> products = service.getProductByCategory(category);
        model.addAttribute("size", products.size());
        model.addAttribute("products", products);
        return "index";
    }

    @ModelAttribute("categories")
    public Category[] getCategories() {
        return Category.values();
    }

    @ModelAttribute("page")
    public String getPage() {
        return "list-product";
    }

}
