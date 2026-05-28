package com.hotpot.deliveryapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.exception.ResourceNotFoundException;
import com.hotpot.deliveryapplication.model.Restaurant;
import com.hotpot.deliveryapplication.repository.RestaurantRepo;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepo repo;

    public Restaurant save(Restaurant restaurant) {
        return repo.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return repo.findAll();
    }

    public Restaurant getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
    }

    public List<Restaurant> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public void deleteRestaurant(int id) {

    Restaurant restaurant = repo
            .findById(id)
            .orElseThrow(() ->
                new RuntimeException("Restaurant not found"));

    restaurant.getMenuItems().clear();

    restaurant.getCategories().clear();

    restaurant.getFeedbacks().clear();

    repo.delete(restaurant);
}
}