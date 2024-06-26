package dev.ta2khu75.java5assignment.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dev.ta2khu75.java5assignment.anotations.ValidPhoneNumber;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data @Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(value={AuditingEntityListener.class})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank
    String address;
    @NotBlank
    @ValidPhoneNumber
    String phoneNumber;
    long total;
    @NotBlank
    String fullName;
    @Enumerated(EnumType.STRING)
    Status status = Status.PENDING;
    @NotNull
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    User user;
    @OneToMany(mappedBy = "order")
    List<OrderDetails> orderDetails;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    LocalDateTime createDate;
    @LastModifiedDate
    @Column(insertable = false)
    LocalDateTime updateDate;
}
