package com.example.onlineFoodOrderingSystem.repository;

import com.example.onlineFoodOrderingSystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
