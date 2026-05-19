package com.hotpot.deliveryapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.MenuItem;

public interface MenuItemRepo extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByRestaurant_RestaurantId(int restaurantId);

    List<MenuItem> findByCategoryCategoryId(int categoryId);

    List<MenuItem> findByVeg(boolean veg);

    List<MenuItem> findByCategoryCategoryIdAndVeg(int categoryId, boolean veg);

    List<MenuItem> findByNameContainingIgnoreCase(String keyword);

    List<MenuItem> findByNameContainingIgnoreCaseAndCategoryCategoryId(String name, int categoryId);

    List<MenuItem> findByNameContainingIgnoreCaseAndCategoryCategoryIdAndVeg(
            String name, int categoryId, boolean veg);


}