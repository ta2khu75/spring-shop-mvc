package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    Page<Product> findByNameContainingAndActiveTrue(String keyword, Pageable pageable);
    List<Product> findByNameContainingAndCategoryContainingAndActiveTrue(String nameKeyword, String categoryKeyword);
    Page<Product> findByCategory(Pageable pageable, Category category);
    Page<Product> findByActiveTrue(Pageable pageable);
}
