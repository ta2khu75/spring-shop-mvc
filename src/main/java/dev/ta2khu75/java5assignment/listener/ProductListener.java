package dev.ta2khu75.java5assignment.listener;

import dev.ta2khu75.java5assignment.models.Product;
import dev.ta2khu75.java5assignment.services.RedisService;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductListener {
    private final RedisService redisService;
    @PrePersist
    public void prePersist(Product product) {

    }

    @PostPersist
    public void postPersist(Product product) {
    }

    @PreUpdate
    public void preUpdate(Product product) {

    }

    @PostUpdate
    public void postUpdate(Product product) {
        redisService.deleteKey("product"+product.getId());
    }

    @PreRemove
    public void preRemove(Product product) {

    }

    @PostRemove
    public void postRemove(Product product) {
        redisService.clear();
    }
}
