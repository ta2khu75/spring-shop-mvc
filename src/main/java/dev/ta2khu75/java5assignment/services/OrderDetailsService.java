package dev.ta2khu75.java5assignment.services;

import java.util.List;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;

public interface OrderDetailsService {
    OrderDetails createOrderDetails(Order orderId, Long productId, Integer quantity);
    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);
}
