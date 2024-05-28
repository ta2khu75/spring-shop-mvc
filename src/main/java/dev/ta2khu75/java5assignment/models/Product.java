package dev.ta2khu75.java5assignment.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import dev.ta2khu75.java5assignment.listener.ProductListener;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Entity @EntityListeners(value={AuditingEntityListener.class, ProductListener.class})
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String name;
    @NotBlank
    String description;
    String imageUrl;
    boolean active = true;
    long numberOfSales;
    @Min(0) @NotNull
    Long price;
    @NotNull @Min(0)
    Integer quantity;
    @Enumerated(EnumType.STRING)
    Category category;
    @OneToMany(mappedBy = "product")
    List<ProductImage> productImages;
    @OneToMany(mappedBy = "product")
    List<OrderDetails> orderDetails;
    @CreatedDate
    LocalDateTime createDate;
}
