package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ta2khu75.java5assignment.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findAllByProductId(Long productId);
}
