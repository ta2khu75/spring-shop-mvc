package dev.ta2khu75.java5assignment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.models.Status;
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByOrderId(Long orderId);
     @Query("SELECT o.product.name, COUNT(o.product) FROM OrderDetails o WHERE o.order.status IN (SHIPPING, SHIPPED, DELIVERED) GROUP BY o.product.name")
    List<Object[]> countProduct();
//     @Query("SELECT o.product.name, count(o.product) FROM OrderDetails WHERE o.Status in (, 'SHIPPED', 'DELIVERED') GROUP BY o.product.name")
//     public List<Object[]> countProduct();
}
