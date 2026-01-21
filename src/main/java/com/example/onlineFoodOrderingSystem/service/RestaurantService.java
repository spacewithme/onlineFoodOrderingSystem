package com.example.onlineFoodOrderingSystem.service;

import com.example.onlineFoodOrderingSystem.entity.Restaurant;
import com.example.onlineFoodOrderingSystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository repository){
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
