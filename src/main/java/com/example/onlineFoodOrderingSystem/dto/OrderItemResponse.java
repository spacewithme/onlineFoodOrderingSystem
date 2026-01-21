package com.example.onlineFoodOrderingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {

    private Long menuItemId;
    private String menuItemName;
    private double price;
    private int quantity;
}
