package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.ta2khu75.java5assignment.models.Category;
import dev.ta2khu75.java5assignment.models.Product;
import io.lettuce.core.dynamic.annotation.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {
        @Query("SELECT p FROM Product p WHERE " +
                        "(:keyword IS NULL OR p.name LIKE %:keyword% OR p.description LIKE %:keyword%) " +
                        "AND (:category IS NULL OR p.category = :category) " +
                        "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
                        "AND (:maxPrice IS NULL OR p.price <= :maxPrice) ")
        Page<Product> searchProduct(
                        @Param("keyword") String keyword,
                        @Param("category") Category category,
                        @Param("minPrice") Long minPrice,
                        @Param("maxPrice") Long maxPrice,
                        Pageable pageable);

        Page<Product> findByNameContainingAndActiveTrue(String keyword, Pageable pageable);

        @Query("SELECT p FROM Product p WHERE p.category = :category AND p.active = true AND p.id <> :productId ORDER BY p.numberOfSales DESC")
        List<Product> findTop4ByCategoryAndActiveTrueAndIdNotEqualOrderByNumberOfSalesDesc(
                        @Param("category") Category category, @Param("productId") Long productId, Pageable pageable);

        List<Product> findByNameContainingAndCategoryContainingAndActiveTrue(String nameKeyword,
                        String categoryKeyword);

        Page<Product> findByCategory(Pageable pageable, Category category);

        Page<Product> findByActiveTrueOrderByCreateDateDesc(Pageable pageable);
}
