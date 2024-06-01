package dev.ta2khu75.java5assignment.services;

import java.util.List;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.OrderDetails;
import dev.ta2khu75.java5assignment.models.Product;

public interface OrderDetailsService {
    OrderDetails createOrderDetails(Order orderId, Product product , Integer quantity);
    List<OrderDetails> getOrderDetailsByOrderId(Long orderId);
    List<Object[]> countProduct();
    List<Object[]> sumPriceGroupByCateogry();
}
