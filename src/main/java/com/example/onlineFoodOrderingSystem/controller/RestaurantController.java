package com.example.onlineFoodOrderingSystem.controller;

import com.example.onlineFoodOrderingSystem.entity.Restaurant;
import com.example.onlineFoodOrderingSystem.service.RestaurantService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    // ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant){
        return service.create(restaurant);
    }

    // USER / RESTAURANT / ADMIN
    @PreAuthorize("hasAnyRole('USER','ADMIN','RESTAURANT')")
    @GetMapping
    public List<Restaurant> getALlRestaurant() {
        return service.getAll();
    }

}
