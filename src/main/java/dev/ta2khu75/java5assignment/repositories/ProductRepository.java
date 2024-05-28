package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContaining(String keyword);
    List<Product> findByNameContainingAndCategoryContaining(String nameKeyword, String categoryKeyword);
    List<Product> findByCategory(Category category);
}
