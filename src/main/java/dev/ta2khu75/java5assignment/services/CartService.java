package dev.ta2khu75.java5assignment.services;

import java.util.Map;

public interface CartService {
    public void addToCart(Long userId, Long productId, Integer quantity);
    public void deleteFromCart(Long userId, Long productId);
    public void clearCart(Long userId);
    public void updateCart(Long userId, Long productId, int quantity);
    public void incrementCart(Long userId, Long productId);
    public void decrementCart(Long userId, Long productId);
    public Map<Long, Integer> getCart(Long userId);
}
