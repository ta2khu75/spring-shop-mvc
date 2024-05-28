package dev.ta2khu75.java5assignment.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Index {
    private final ProductService service;

    @RequestMapping
    public String requestMethodName() {
        return "index";
    }

    @ModelAttribute("categories")
    public Category[] categories() {
        return Category.values();
    }

    @ModelAttribute("products")
    public List<Product> products() throws JsonMappingException, JsonProcessingException, ClassNotFoundException {
        return service.getAllProducts();
    }
    @ModelAttribute("page")
    public String getPage(){
        return "home";
    }

}
