package com.example.onlineFoodOrderingSystem.repository;

import com.example.onlineFoodOrderingSystem.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
}
