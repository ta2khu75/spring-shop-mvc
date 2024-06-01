package dev.ta2khu75.java5assignment.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/list-product")
@RequiredArgsConstructor
public class ListProduct {
    private final ProductService service;
    private final int SIZE = 6;

    @GetMapping
    public String getMethodName(@RequestParam(required = false) String category,
            @RequestParam(required = false) Optional<String> keyword,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Long> min,
            @RequestParam(required = false) Optional<Long> max,
            @RequestParam(required = false) String sortType,
            Model model) {
        Sort sort;
        if (sortType == null || sortType.isBlank()) {
            sort = Sort.by(Sort.Order.desc("numberOfSales"));
        } else {
            if (sortType.equals("asc")) {
                sort = Sort.by("price").ascending();
            } else {
                sort = Sort.by("price").descending();
            }
        }
        Pageable pageable = PageRequest.of(page.orElse(0), SIZE, sort);
        Category categoryType = null;
        if (category != null && !category.isBlank()) {
            try {
                categoryType = Category.valueOf(category);
            } catch (Exception e) {
                categoryType = null;
            }
        }
        Page<Product> products = service.searchProductPage(keyword.orElse(null), categoryType,
                min.orElse(null), max.orElse(null), pageable);
        model.addAttribute("products", products);
        model.addAttribute("size", products.getContent().size());
        model.addAttribute("category", categoryType);
        model.addAttribute("keyword", keyword.orElse(null));
        model.addAttribute("min", min.orElse(null));
        model.addAttribute("max", max.orElse(null));
        model.addAttribute("sortType", sortType);
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
