package com.hotpot.deliveryapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.Restaurant;


public interface RestaurantRepo extends JpaRepository<Restaurant, Integer>{
    

    public List<Restaurant> findByNameContainingIgnoreCase(String name);
    
}

