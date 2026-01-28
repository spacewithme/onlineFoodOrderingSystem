package com.example.onlineFoodOrderingSystem.repository;

import com.example.onlineFoodOrderingSystem.entity.Order;
import com.example.onlineFoodOrderingSystem.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
    Page<Order> findByUserEmailOrderByOrderTimeDesc(String email, Pageable pageable);

    Page<Order> findByStatusOrderByOrderTimeDesc(OrderStatus status, Pageable pageable);

    Page<Order> findAllByOrderByOrderTimeDesc(Pageable pageable);
}
