package com.example.onlineFoodOrderingSystem.dto;

import lombok.Setter;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Long id;
    private LocalDateTime orderTime;
    private String status;
    private double totalAmount;
    private List<OrderItemResponse> items;
}
