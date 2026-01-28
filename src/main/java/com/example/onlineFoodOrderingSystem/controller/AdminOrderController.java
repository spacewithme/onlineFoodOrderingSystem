package com.example.onlineFoodOrderingSystem.controller;

import com.example.onlineFoodOrderingSystem.dto.OrderResponse;
import com.example.onlineFoodOrderingSystem.dto.UpdateOrderStatusRequest;
import com.example.onlineFoodOrderingSystem.entity.OrderStatus;
import com.example.onlineFoodOrderingSystem.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Page<OrderResponse> getALlOrders(@RequestParam(required = false)OrderStatus status, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return service.getAllOrders(status, page, size);
    }
}
