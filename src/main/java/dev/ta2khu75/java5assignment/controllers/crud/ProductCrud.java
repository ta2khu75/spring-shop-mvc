package dev.ta2khu75.java5assignment.controllers.crud;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.ta2khu75.java5assignment.exceptions.UnAuthorizationException;
import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.models.Role;
import dev.ta2khu75.java5assignment.resps.UserResp;
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

    @GetMapping
    public String getMethodName(@ModelAttribute Product product) {
            return "crud/product";
    }

    @PostMapping
    public String postMethodName(Model model, @RequestParam MultipartFile image, @Valid @ModelAttribute Product product,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (product.getId() == null) {
                if (!image.isEmpty()) {
                    try {
                        service.createProduct(product, image);
                        return "redirect:/crud/product";
                    } catch (IOException e) {
                        e.printStackTrace();
                        model.addAttribute("message", "Create failed");
                    }
                } else {
                    model.addAttribute("message", "You must upload an image");
                }
            } else {
                try {
                    service.updateProduct(product, image);
                    return "redirect:/crud/product";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "crud/product";
    }

    @GetMapping("{id}")
    public String getMethodName(@PathVariable Long id, Model model)
            throws JsonProcessingException, ClassNotFoundException {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "crud/product";

    }

    @GetMapping("delete/{id}")
    public String getMethodName(@PathVariable Long id) {
        service.deleteProduct(id);
        return "redirect:/crud/product";
    }

    @GetMapping("view/{id}")
    public String getMethodView(@PathVariable Long id, Model model)
            throws IOException, ClassNotFoundException {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "productDetails";
    }

    @ModelAttribute("products")
    public List<Product> getProducts() throws JsonMappingException, JsonProcessingException, ClassNotFoundException {
        return service.getAllProducts();
    }

    @ModelAttribute("categories")
    public Category[] getCategories() {
        return Category.values();
    }

    @ModelAttribute
    public void getAuthorization(@SessionAttribute("user") UserResp userResp) {
        if (userResp == null || !userResp.getRole().equals(Role.ADMIN)) {
            throw new UnAuthorizationException("Access denied");
        }
    }

}
