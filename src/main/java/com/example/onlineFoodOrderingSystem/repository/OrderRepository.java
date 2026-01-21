package com.example.onlineFoodOrderingSystem.repository;

import com.example.onlineFoodOrderingSystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    List<Order> findByUserEmailOrderByOrderTimeDesc(String email);
}
