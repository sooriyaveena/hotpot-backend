package com.hotpot.deliveryapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.model.Category;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<ApiResponse<List<Category>>> getByRestaurant(@PathVariable int restaurantId) {
        List<Category> categories = service.getCategoriesByRestaurant(restaurantId);

        return ResponseEntity.ok(
                ApiResponse.<List<Category>>builder()
                        .success(true)
                        .message("Categories fetched successfully")
                        .data(categories)
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Category>> save(@RequestBody Category c) {
        Category saved = service.save(c);

        return ResponseEntity.ok(
                ApiResponse.<Category>builder()
                        .success(true)
                        .message("Category saved successfully")
                        .data(saved)
                        .build()
        );
    }

    @GetMapping("/all")
public ResponseEntity<ApiResponse<List<Category>>> getAll() {

    List<Category> categories =
            service.getAll();

    return ResponseEntity.ok(

            ApiResponse.<List<Category>>builder()
                    .success(true)
                    .message("Categories fetched successfully")
                    .data(categories)
                    .build()
    );
}
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<String>> delete(
        @PathVariable int id) {

    service.deleteCategory(id);

    return ResponseEntity.ok(

            ApiResponse.<String>builder()
                    .success(true)
                    .message("Category deleted successfully")
                    .data(null)
                    .build()
    );
}
}