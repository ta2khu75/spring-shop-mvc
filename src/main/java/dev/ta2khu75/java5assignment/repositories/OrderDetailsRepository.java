package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.ta2khu75.java5assignment.models.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByOrderId(Long orderId);

    @Query("SELECT o.product, count(o.product) FROM OrderDetails o GROUP BY o.product")
    public List<Object[]> countProduct();
}
