package com.hotpot.deliveryapplication.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.Category;



public interface CategoryRepo extends JpaRepository<Category, Integer> {
	List<Category> findByRestaurantRestaurantId(int restaurantId);
}


