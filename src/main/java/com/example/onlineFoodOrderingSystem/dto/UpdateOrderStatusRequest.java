package com.example.onlineFoodOrderingSystem.dto;
import jakarta.validation.constraints.NotNull;

import com.example.onlineFoodOrderingSystem.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {

    @NotNull(message = "status is required")
    private OrderStatus status;

}
