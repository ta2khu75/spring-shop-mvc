package dev.ta2khu75.java5assignment.services;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;

public interface ProductService {
    public void deleteProduct(Long id);
    public Product getProductById(Long id) throws JsonProcessingException, ClassNotFoundException;
    public Product updateProduct(Product product, MultipartFile image) throws IOException;
    public Product createProduct(Product product, MultipartFile image) throws IOException;
    public Page<Product> getAllProducts(Pageable pageable) throws JsonProcessingException;
    public Page<Product> getAllProductsActiveTrue(Pageable pageable) throws JsonProcessingException;
    public Page<Product> getProductNameByKeyword(Pageable pageable,String keyword);
    public Page<Product> getProductByCategory(Pageable pageable, Category category);
    public List<Product> getProductByKeywordAndCategory(String keyword);
}
