package dev.ta2khu75.java5assignment.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.Status;

public interface OrderService {
    public Order createOrder(Long userId, Order order);
    public Order getOrderById(Long id);
    public void deleteOrder(Long id);
    public Order updateOrder(Order order);
    public Order findTopByUserIdOrderByIdDesc(Long id);
    public Page<Order> getAllOrders(Pageable pageable);
    public List<Order> getOrderByUserId(Long id);
    public List<Order> getOrderByUserIdAndStatus(Long userId, Status status);
    public List<Order> getNewOrder(Long userId);
    public List<Order> getOldOrder(Long userId);
    public List<Object[]> getTotalOrderGroupDay();
}
