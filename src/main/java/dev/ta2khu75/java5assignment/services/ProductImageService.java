package dev.ta2khu75.java5assignment.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import dev.ta2khu75.java5assignment.models.ProductImage;

public interface ProductImageService {
    public void deleteProductImage(Long id);
    public ProductImage createProductImage(Long productId, MultipartFile image) throws IOException;
    public ProductImage updateProductImage(Long id, MultipartFile image) throws IOException;
    public List<ProductImage> getAllProductImagesByProductId(Long productId);
}
