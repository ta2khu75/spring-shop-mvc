package dev.ta2khu75.java5assignment.services;

import java.util.List;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.Status;

public interface OrderService {
    public Order createOrder(Long userId, Order order);
    public Order getOrderById(Long id);
    public void deleteOrder(Long id);
    public Order updateOrder(Order order);
    public List<Order> getOrderByUserId(Long id);
    public Order findTopByUserIdOrderByIdDesc(Long id);
    public List<Order> getOrderByUserIdAndStatus(Long userId, Status status);
    public List<Order> getNewOrder(Long userId);
    public List<Order> getOldOrder(Long userId);
}
