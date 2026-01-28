package com.example.onlineFoodOrderingSystem.controller;

import com.example.onlineFoodOrderingSystem.dto.OrderResponse;
import com.example.onlineFoodOrderingSystem.dto.PlaceOrderRequest;
import com.example.onlineFoodOrderingSystem.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // User access only
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public OrderResponse placeOrder(@RequestBody PlaceOrderRequest request, Authentication authentication) {
        return service.placeOrder(authentication.getName(),request);
    }

    @GetMapping("/my")
    public Page<OrderResponse> getMyOrders(Authentication authentication, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        return service.getMyOrders(authentication.getName(),page,size);
    }

    @PreAuthorize("hasRole('USER')")
    @PatchMapping("/{orderId}/cancel")
    public OrderResponse cancelOrder(@PathVariable Long orderId, Authentication authentication) {
        return service.cancelOrder(orderId, authentication.getName());
    }

}
