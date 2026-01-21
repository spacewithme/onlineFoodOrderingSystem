package com.example.onlineFoodOrderingSystem.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderRequest {
    private List<Item> items;

    @Getter @Setter
    public static class Item {
        private Long menuItemId;
        private int quantity;
    }
}
