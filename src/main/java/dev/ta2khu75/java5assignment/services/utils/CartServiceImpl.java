package dev.ta2khu75.java5assignment.services.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import dev.ta2khu75.java5assignment.services.CartService;

@Service
@ApplicationScope
public class CartServiceImpl implements CartService {
    private Map<Long, Map<Long, Integer>> cart = new LinkedHashMap<>();

    @Override
    public void addToCart(Long userId, Long productId, Integer quantity) {
        if (!cart.containsKey(userId)) {
            Map<Long, Integer> map = new LinkedHashMap<>();
            map.put(productId, quantity);
            cart.put(userId, map);
        } else {
            if (!cart.get(userId).containsKey(productId)) {
                cart.get(userId).put(productId, quantity);
            } else {
                Map<Long, Integer> map = cart.get(userId);
                map.put(productId, map.get(productId) + quantity);
                cart.put(userId, map);
            }
        }
    }

    @Override
    public void deleteFromCart(Long userId, Long productId) {
        if (cart.containsKey(userId)) {
            Map<Long, Integer> map = cart.get(userId);
            map.remove(productId);
            cart.put(userId, map);
        }
    }

    @Override
    public void clearCart(Long userId) {
        if (cart.containsKey(userId)) {
            cart.remove(userId);
        }
    }

    @Override
    public void updateCart(Long userId, Long productId, int quantity) {
        if (cart.containsKey(userId)) {
            Map<Long, Integer> map = cart.get(userId);
            map.put(productId, quantity);
            cart.put(userId, map);
        }
    }

    @Override
    public void incrementCart(Long userId, Long productId) {
        if (cart.containsKey(userId)) {
            Map<Long, Integer> map = cart.get(userId);
            map.put(productId, map.get(productId) + 1);
            cart.put(userId, map);
        }
    }

    @Override
    public void decrementCart(Long userId, Long productId) {
        if (cart.containsKey(userId)) {
            Map<Long, Integer> map = cart.get(userId);
            if (map.get(productId) == 1) {
                map.remove(productId);
                cart.put(userId, map);
                return;
            }
            map.put(productId, map.get(productId) - 1);
            cart.put(userId, map);
        }
    }

    @Override
    public Map<Long, Integer> getCart(Long userId) {
        if (cart.containsKey(userId)) {
            return cart.get(userId);
        }
        return null;
    }
}
