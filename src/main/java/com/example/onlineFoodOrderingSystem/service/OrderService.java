package com.example.onlineFoodOrderingSystem.service;


import com.example.onlineFoodOrderingSystem.dto.PlaceOrderRequest;
import com.example.onlineFoodOrderingSystem.dto.OrderItemResponse;
import com.example.onlineFoodOrderingSystem.dto.OrderResponse;
import com.example.onlineFoodOrderingSystem.dto.UpdateOrderStatusRequest;
import com.example.onlineFoodOrderingSystem.entity.*;
import com.example.onlineFoodOrderingSystem.repository.*;
import org.springframework.data.web.config.SortHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final MenuItemRepository menuRepo;
    private final UserRepository userRepo;
    private final OrderStatusHistoryRepository historyRepo;
    private final SortHandlerMethodArgumentResolverCustomizer sortHandlerMethodArgumentResolverCustomizer;

    public OrderService(OrderRepository orderRepo, MenuItemRepository menuRepo, UserRepository userRepo, OrderStatusHistoryRepository historyRepo, SortHandlerMethodArgumentResolverCustomizer sortHandlerMethodArgumentResolverCustomizer) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.menuRepo = menuRepo;
        this.historyRepo = historyRepo;
        this.sortHandlerMethodArgumentResolverCustomizer = sortHandlerMethodArgumentResolverCustomizer;
    }

    private void saveHistory(Order order, OrderStatus status, String changedBy) {
        OrderStatusHistory history = new OrderStatusHistory();
        history.setOrder(order);
        history.setStatus(status);
        history.setChangedBy(changedBy);
        history.setChangedAt(LocalDateTime.now());
        historyRepo.save(history);
    }

    public OrderResponse placeOrder(String email, PlaceOrderRequest request) {

        User user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PLACED);
        order.setOrderTime(LocalDateTime.now());

        double total = 0;

        for(PlaceOrderRequest.Item reqItem : request.getItems()) {
            MenuItem menuItem = menuRepo.findById(reqItem.getMenuItemId()).orElseThrow(() -> new RuntimeException("Menu Item not found"));

            OrderItem item = new OrderItem();
            item.setMenuItem(menuItem);
            item.setQuantity(reqItem.getQuantity());
            item.setOrder(order);

            order.getItems().add(item);
            total += menuItem.getPrice() * reqItem.getQuantity();
        }
        order.setTotalAmount(total);
        Order savedOrder = orderRepo.save(order);
        saveHistory(savedOrder, OrderStatus.PLACED, "USER");
        return mapToOrderResponse(savedOrder);
    }

    public List<OrderResponse> getMyOrders(String email) {

        return orderRepo.findByUserEmailOrderByOrderTimeDesc(email)
                .stream()
                .map(this::mapToOrderResponse)
                .toList();
    }

    private OrderResponse mapToOrderResponse(Order order){
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrderTime(order.getOrderTime());
        response.setStatus(order.getStatus().name());
        response.setTotalAmount(order.getTotalAmount());

        List<OrderItemResponse> items = order.getItems().stream().map(item -> {
            OrderItemResponse dto = new OrderItemResponse();
            dto.setMenuItemId(item.getMenuItem().getId());
            dto.setMenuItemName(item.getMenuItem().getName());
            dto.setPrice(item.getMenuItem().getPrice());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).toList();
        response.setItems(items);
        return response;
    }

    public OrderResponse updateOrderStatus(Long orderId, UpdateOrderStatusRequest request) {
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

        OrderStatus current = order.getStatus();
        OrderStatus newStatus = request.getStatus();

        if(!current.canTransitionTo(newStatus)) {
            throw new IllegalStateException("Invalid status transition: "+ current + " -> " + newStatus);
        }
        order.setStatus(newStatus);
        Order updatedOrder = orderRepo.save(order);

        saveHistory(updatedOrder, newStatus, "ADMIN");

        return mapToOrderResponse(updatedOrder);
    }

}
