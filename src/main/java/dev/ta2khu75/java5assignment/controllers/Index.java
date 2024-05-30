package dev.ta2khu75.java5assignment.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Index {
    private final ProductService service;
    private final int SIZE=4;

    @RequestMapping
    public String requestMethodName() {
        return "index";
    }

    @ModelAttribute("categories")
    public Category[] categories() {
        return Category.values();
    }

    @ModelAttribute("products")
    public Page<Product> products(@RequestParam(required = false) Optional<Integer> page) throws JsonProcessingException {
        Pageable pageable= PageRequest.of(page.orElse(0), SIZE);
        return service.getAllProductsActiveTrue(pageable);
    }

    @ModelAttribute("page")
    public String getPage() {
        return "home";
    }

}
