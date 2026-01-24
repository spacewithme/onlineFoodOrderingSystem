package com.example.onlineFoodOrderingSystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderStatusHistoryResponse {

    private String status;
    private String changedBy;
    private LocalDateTime changedAt;
}
