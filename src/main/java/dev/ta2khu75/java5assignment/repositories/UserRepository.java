package dev.ta2khu75.java5assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.ta2khu75.java5assignment.models.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
