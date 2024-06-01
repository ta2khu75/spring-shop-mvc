package dev.ta2khu75.java5assignment.controllers.crud;

import java.io.IOException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("crud/product")
@RequiredArgsConstructor
public class ProductCrud {
    private final ProductService service;
    private final int SIZE = 10;

    @GetMapping
    public String getMethodName(@ModelAttribute Product product, @RequestParam(required = false) boolean errorCreate,
            @RequestParam(required = false) boolean errorUpdate, @RequestParam(required = false) boolean errorDelete,
            @RequestParam(required = false) boolean successCreate,
            @RequestParam(required = false) boolean successDelete,
            @RequestParam(required = false) boolean successUpdate,
            Model model) {
        model.addAttribute("errorCreate", errorCreate);
        model.addAttribute("errorUpdate", errorUpdate);
        model.addAttribute("errorDelete", errorDelete);
        model.addAttribute("successCreate", successCreate);
        model.addAttribute("successDelete", successDelete);
        model.addAttribute("successUpdate", successUpdate);
        return "crud/admin";
    }

    @PostMapping
    public String postMethodName(@RequestParam int pages, Model model, @RequestParam MultipartFile image,
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (product.getId() == null) {
                if (!image.isEmpty()) {
                    try {
                        service.createProduct(product, image);
                        return "redirect:/crud/product?successCreate=true&pages=" + pages;
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("errorCreate", true);
                    }
                } else {
                    model.addAttribute("errorNotImage", true);
                }
            } else {
                try {
                    service.updateProduct(product, image);
                    return "redirect:/crud/product?successUpdate=true&pages=" + pages;
                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("errorUpdate", true);
                }
            }
        } else {
            model.addAttribute("errorValid", true);
        }
        model.addAttribute("pages", pages);
        return "crud/admin";
    }

    @GetMapping("edit")
    public String getMethodName(@RequestParam Long id, Model model)
            throws JsonProcessingException {
        try {
            Product product = service.getProductById(id);
            model.addAttribute("product", product);
        } catch (Exception e) {
            model.addAttribute("errorNotExist", true);
            model.addAttribute("product", new Product());
        }
        return "crud/admin";
    }

    @GetMapping("delete")
    public String getMethodName(@RequestParam Long id, @RequestParam(required = false) int pages) {
        try {
            service.deleteProduct(id);
            return "redirect:/crud/product?successDelete=true&pages=" + pages;
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/crud/product?errorDelete=true&pages=" + pages;
        }
    }

    @GetMapping("view/{id}")
    public String getMethodView(@PathVariable Long id, Model model)
            throws IOException{
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("page", "product-details");
        return "crud/admin";
    }

    @ModelAttribute("products")
    public Page<Product> getProducts(@RequestParam(required = false) Optional<Integer> pages, Model model)
            throws JsonProcessingException {
        model.addAttribute("pages", pages.orElse(0));
        Pageable pageable = PageRequest.of(pages.orElse(0), SIZE);
        return service.getAllProducts(pageable);
    }

    @ModelAttribute("categories")
    public Category[] getCategories() {
        return Category.values();
    }

    @ModelAttribute("page")
    public String getPage() {
        return "product";
    }

}
