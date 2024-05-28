package dev.ta2khu75.java5assignment.services.impls;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.ta2khu75.java5assignment.exceptions.NotFoundException;
import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.Status;
import dev.ta2khu75.java5assignment.models.User;
import dev.ta2khu75.java5assignment.repositories.OrderRepository;
import dev.ta2khu75.java5assignment.repositories.UserRepository;
import dev.ta2khu75.java5assignment.services.OrderService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final List<Status> statusesOld = List.of(Status.CANCELED, Status.DELIVERED, Status.SHIPPED);
    private final List<Status> statusesNew = List.of(Status.PROCESSING, Status.PENDING, Status.SHIPPING);
    private final OrderRepository repository;
    private final UserRepository userRepository;

    @Override
    public Order createOrder(Long userId, Order order) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        order.setUser(user);
        order.setStatus(Status.PENDING);
        return repository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Order not found"));
    }

    @Override
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getOrderByUserId(Long id) {
        return repository.findByUserId(id);
    }

    @Override
    public Order findTopByUserIdOrderByIdDesc(Long id) {
        return repository.findTopByUserIdOrderByIdDesc(id).orElse(null);
    }

    @Override
    public List<Order> getOrderByUserIdAndStatus(Long userId, Status status) {
        return repository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public List<Order> getNewOrder(Long userId) {
        return repository.findByUserIdAndStatuses(userId, statusesNew);
    }

    @Override
    public List<Order> getOldOrder(Long userId) {
        return repository.findByUserIdAndStatuses(userId, statusesOld);
    }

}
