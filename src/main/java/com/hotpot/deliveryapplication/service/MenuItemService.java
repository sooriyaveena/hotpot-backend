package com.hotpot.deliveryapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.model.MenuItem;
import com.hotpot.deliveryapplication.repository.MenuItemRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepo repo;

    public MenuItem save(MenuItem item) {

        return repo.save(item);
    }

    public List<MenuItem> getAll() {

        return repo.findAll();
    }

    public List<MenuItem> filterItems(
            Integer categoryId,
            String dietaryType,
            String keyword
    ) {

        if (
                keyword != null &&
                !keyword.isEmpty() &&
                categoryId != null &&
                dietaryType != null
        ) {

            return repo
                    .findByNameContainingIgnoreCaseAndCategoryCategoryIdAndDietaryType(
                            keyword,
                            categoryId,
                            dietaryType
                    );
        }

        if (
                keyword != null &&
                !keyword.isEmpty() &&
                categoryId != null
        ) {

            return repo
                    .findByNameContainingIgnoreCaseAndCategoryCategoryId(
                            keyword,
                            categoryId
                    );
        }

        if (
                keyword != null &&
                !keyword.isEmpty()
        ) {

            return repo
                    .findByNameContainingIgnoreCase(
                            keyword
                    );
        }

        if (
                categoryId != null &&
                dietaryType != null
        ) {

            return repo
                    .findByCategoryCategoryIdAndDietaryType(
                            categoryId,
                            dietaryType
                    );
        }

        if (categoryId != null) {

            return repo
                    .findByCategoryCategoryId(
                            categoryId
                    );
        }

        if (dietaryType != null) {

            return repo
                    .findByDietaryType(
                            dietaryType
                    );
        }

        return repo.findAll();
    }

    public void deleteMenuItem(int id) {

        MenuItem item =
                repo.findById(id)

                .orElseThrow(() ->
                        new RuntimeException(
                                "Menu item not found"
                        )
                );

        repo.delete(item);
    }

    public List<MenuItem>
    getByRestaurant(int id) {

        return repo
                .findByRestaurant_RestaurantId(id);
    }
}