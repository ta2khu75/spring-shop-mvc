package dev.ta2khu75.java5assignment.controllers.crud;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import dev.ta2khu75.java5assignment.models.ProductImage;
import dev.ta2khu75.java5assignment.services.ProductImageService;
import dev.ta2khu75.java5assignment.services.ProductService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("crud/product-image/{productId}")
@RequiredArgsConstructor
public class ProductImageCrud {
    private final ProductService productService;
    private final ProductImageService service;

    @GetMapping
    public String getMethodName(@PathVariable Long productId, Model model) throws IOException{
        model.addAttribute("product", productService.getProductById(productId));
        return "crud/admin";
    }

    @PostMapping
    public String postMethodName(@PathVariable Long productId, @RequestParam MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            service.createProductImage(productId, image);
            return "redirect:/crud/product-image/" + productId;
        } else {
            return "redirect:/crud/product-image/" + productId + "?error=true";
        }
    }

    @GetMapping("delete/{imageId}")
    public String getMethodName(@PathVariable Long imageId, @PathVariable Long productId) {
        service.deleteProductImage(imageId);
        return "redirect:/crud/product-image/" + productId;
    }

    @PostMapping("{imageId}")
    public String postMethodName(@PathVariable Long imageId, @PathVariable Long productId,
            @RequestParam MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            service.updateProductImage(imageId, image);
        }
        return "redirect:/crud/product-image/" + productId;
    }

    @ModelAttribute("productImages")
    public List<ProductImage> getMethodName(@PathVariable Long productId) {
        return service.getAllProductImagesByProductId(productId);
    }

    @ModelAttribute("page")
    public String getPage(){
        return "product-details";
    }
}
