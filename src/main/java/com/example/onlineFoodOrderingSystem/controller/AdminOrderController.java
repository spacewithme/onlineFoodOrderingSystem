package com.example.onlineFoodOrderingSystem.controller;

import com.example.onlineFoodOrderingSystem.dto.OrderResponse;
import com.example.onlineFoodOrderingSystem.dto.UpdateOrderStatusRequest;
import com.example.onlineFoodOrderingSystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderService service;

    public AdminOrderController(OrderService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{orderId}/status")
    public OrderResponse updateOrderStatus(@PathVariable Long orderId, @Valid @RequestBody UpdateOrderStatusRequest request) {
        return service.updateOrderStatus(orderId, request);
    }
}
