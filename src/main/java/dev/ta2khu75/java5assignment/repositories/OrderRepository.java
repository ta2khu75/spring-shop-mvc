package dev.ta2khu75.java5assignment.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.ta2khu75.java5assignment.models.Order;
import dev.ta2khu75.java5assignment.models.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {
   List<Order> findByUserId(Long userId);

   Optional<Order> findTopByUserIdOrderByIdDesc(Long id);

   List<Order> findByUserIdAndStatus(Long userId, Status status);

   @Query("SELECT o FROM Order o WHERE o.user.id = :userId AND o.status IN :statuses")
   List<Order> findByUserIdAndStatuses(@Param("userId") Long userId, @Param("statuses") List<Status> statuses);
}
