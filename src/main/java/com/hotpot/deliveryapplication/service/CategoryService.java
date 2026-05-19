package com.hotpot.deliveryapplication.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.model.Category;
import com.hotpot.deliveryapplication.repository.CategoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

   
    private final CategoryRepo repo;

    public Category save(Category category) {
        return repo.save(category);
    }

    public List<Category> getAll() {
        return repo.findAll();
    }

    public List<Category> getCategoriesByRestaurant(int restaurantId) {
        return repo.findByRestaurantRestaurantId(restaurantId);
    }
    public void deleteCategory(int id) {

    Category category =
            repo.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Category not found"));

    repo.delete(category);
}
}

