package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import dev.ta2khu75.java5assignment.models.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByOrderId(Long orderId);

    @Query("SELECT o.product.name, COUNT(o.product) FROM OrderDetails o WHERE o.order.status IN (SHIPPING, SHIPPED, DELIVERED) GROUP BY o.product.name")
    List<Object[]> countProduct();
    @Query("SELECT o.product.category, sum(o.product.price*o.quantity) FROM OrderDetails o WHERE o.order.status IN (SHIPPING, SHIPPED, DELIVERED) GROUP BY o.product.category")
    List<Object[]> sumPriceGroupByCategory();
}
