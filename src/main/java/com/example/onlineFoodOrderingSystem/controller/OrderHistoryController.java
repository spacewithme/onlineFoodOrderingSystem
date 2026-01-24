package com.example.onlineFoodOrderingSystem.controller;

import com.example.onlineFoodOrderingSystem.dto.OrderStatusHistoryResponse;
import com.example.onlineFoodOrderingSystem.entity.OrderStatusHistory;
import com.example.onlineFoodOrderingSystem.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderHistoryController {

    private final OrderService service;

    private OrderHistoryController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{orderId}/history")
    public List<OrderStatusHistoryResponse> getOrderHistory(@PathVariable Long orderId, Authentication authentication) {
        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        return service.getOrderHistory(
                orderId,
                authentication.getName(),
                isAdmin
        );
    }
}
