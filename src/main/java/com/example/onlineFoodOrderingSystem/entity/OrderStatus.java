package com.example.onlineFoodOrderingSystem.entity;

public enum OrderStatus {
    PLACED,
    PREPARING,
    DELIVERED,
    CANCELLED;

    public boolean canTransitionTo(OrderStatus next) {
        return switch (this) {
            case PLACED -> next == PREPARING || next == CANCELLED;

            case PREPARING -> next == DELIVERED || next == CANCELLED;

            case DELIVERED -> false;

            case CANCELLED -> false;
        };
    }

    public boolean canBeCancelled() {
        return this == PLACED || this == PREPARING;
    }
}
