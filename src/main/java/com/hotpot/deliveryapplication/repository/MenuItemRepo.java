package com.hotpot.deliveryapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.MenuItem;

public interface MenuItemRepo
        extends JpaRepository<MenuItem, Integer> {

    List<MenuItem>
    findByRestaurant_RestaurantId(
            int restaurantId
    );

    List<MenuItem>
    findByCategoryCategoryId(
            int categoryId
    );

    List<MenuItem>
    findByDietaryType(
            String dietaryType
    );

    List<MenuItem>
    findByCategoryCategoryIdAndDietaryType(
            int categoryId,
            String dietaryType
    );

    List<MenuItem>
    findByNameContainingIgnoreCase(
            String keyword
    );

    List<MenuItem>
    findByNameContainingIgnoreCaseAndCategoryCategoryId(
            String name,
            int categoryId
    );

    List<MenuItem>
    findByNameContainingIgnoreCaseAndCategoryCategoryIdAndDietaryType(
            String name,
            int categoryId,
            String dietaryType
    );
}