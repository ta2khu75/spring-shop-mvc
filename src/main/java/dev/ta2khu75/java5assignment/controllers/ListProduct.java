package dev.ta2khu75.java5assignment.controllers;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getMethodName(@RequestParam String type, @RequestParam String keyword,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Long> min,
            @RequestParam(required = false) Optional<Long> max, Model model) {
                System.out.println(min.orElse(null));
                System.out.println(max.orElse(null));
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Pageable pageable = PageRequest.of(page.orElse(0), SIZE);
        Page<Product> products;
        if (type.equals("category")) {
            if (min.isPresent() && max.isPresent()) {
                products = service.getProductByCategoryAndPriceBetween(pageable, keyword, min.get(), max.get());
            } else if (min.isPresent() && !max.isPresent()) {
                products = service.getProductByCategoryAndPriceGreater(pageable, keyword, min.get());
            } else if (max.isPresent() && !min.isPresent()) {
                products = service.getProductByCategoryAndPriceLess(pageable, keyword, max.get());
            } else {
                products = service.getProductByCategory(pageable, Category.valueOf(keyword));
            }
        } else {
            if (min.isPresent() && max.isPresent()) {
                products = service.getProductByKeywordAndPriceBetween(pageable, keyword, min.get(), max.get());
            } else if (min.isPresent() && !max.isPresent()) {
                products = service.getProductByKeywordAndPriceGreater(pageable, keyword, min.get());
            } else if (max.isPresent() && !min.isPresent()) {
                products = service.getProductByKeywordAndPriceLess(pageable, keyword, max.get());
            } else {
                products = service.getProductNameByKeyword(pageable, keyword);
            }
        }
        model.addAttribute("products", products);
        model.addAttribute("size", products.getContent().size());
        model.addAttribute("type", type);
        model.addAttribute("keyword", keyword);
        model.addAttribute("min", min.orElse(null));
        model.addAttribute("max", max.orElse(null));
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
