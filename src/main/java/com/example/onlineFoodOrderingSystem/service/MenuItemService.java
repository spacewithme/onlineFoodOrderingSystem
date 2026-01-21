package com.example.onlineFoodOrderingSystem.service;

import com.example.onlineFoodOrderingSystem.repository.MenuItemRepository;
import com.example.onlineFoodOrderingSystem.entity.MenuItem;
import com.example.onlineFoodOrderingSystem.entity.Restaurant;
import com.example.onlineFoodOrderingSystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    public final MenuItemRepository menuItemRepository;
    public final RestaurantRepository restaurantRepository;

    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository){
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItem addMenuItem(Long restaurantId, MenuItem item) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("Restaurant not found"));

        item.setRestaurant(restaurant);
        return menuItemRepository.save(item);
    }

    public List<MenuItem> getMenuByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteById(id);
    }
}
