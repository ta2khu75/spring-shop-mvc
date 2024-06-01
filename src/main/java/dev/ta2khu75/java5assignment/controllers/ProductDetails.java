package dev.ta2khu75.java5assignment.controllers;

import java.io.IOException;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductDetails {
    private final ProductService service;
    private final int SIZE = 4;

    @RequestMapping("product-details/{id}")
    public String requestMethodName(@PathVariable Long id, Model model) throws IOException {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        Pageable pageable = PageRequest.of(0, 4);
        model.addAttribute("products", service.getProductRecommendList(product.getCategory(), id, pageable));
        return "index";
    }

    @ModelAttribute("page")
    public String getPage() {
        return "product-details";
    }
}
