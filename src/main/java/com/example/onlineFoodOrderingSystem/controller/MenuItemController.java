package com.example.onlineFoodOrderingSystem.controller;

import com.example.onlineFoodOrderingSystem.entity.MenuItem;
import com.example.onlineFoodOrderingSystem.service.MenuItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    private final MenuItemService service;

    public MenuItemController(MenuItemService service){
        this.service = service;
    }

    // RESTAURANT only access
    @PreAuthorize("hasRole('RESTAURANT')")
    @PostMapping("/{restaurantId}")
    public MenuItem addMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItem item){
        return service.addMenuItem(restaurantId, item);
    }

    // Any Authenticated User
    @PreAuthorize("hasAnyRole('USER', 'RESTAURANT', 'ADMIN')")
    @GetMapping("/{restaurantId}")
    public List<MenuItem> getMenu(@PathVariable Long restaurantId){
        return service.getMenuByRestaurant(restaurantId);
    }

    // RESTAURANT only access
    @PreAuthorize("hasRole('RESTAURANT')")
    @DeleteMapping("{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        service.deleteMenuItem(id);
    }
}
