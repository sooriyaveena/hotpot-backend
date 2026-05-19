package com.hotpot.deliveryapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.model.Restaurant;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.RestaurantService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;




@RestController
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService service;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Restaurant>>> getAll() {
        List<Restaurant> list = service.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<Restaurant>>builder()
                        .success(true)
                        .message("Restaurants fetched successfully")
                        .data(list)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Restaurant>> getById(@PathVariable int id) {
        Restaurant restaurant = service.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<Restaurant>builder()
                        .success(true)
                        .message("Restaurant fetched successfully")
                        .data(restaurant)
                        .build()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Restaurant>>> search(@RequestParam String name) {
        List<Restaurant> list = service.searchByName(name);

        return ResponseEntity.ok(
                ApiResponse.<List<Restaurant>>builder()
                        .success(true)
                        .message("Search results fetched")
                        .data(list)
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Restaurant>> save(
            @Valid @RequestBody Restaurant restaurant) {

        Restaurant saved = service.save(restaurant);

        return ResponseEntity.ok(
                ApiResponse.<Restaurant>builder()
                        .success(true)
                        .message("Restaurant created")
                        .data(saved)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<String>> delete(
        @PathVariable int id) {

    service.deleteRestaurant(id);

    return ResponseEntity.ok(
            ApiResponse.<String>builder()
                    .success(true)
                    .message("Restaurant deleted successfully")
                    .data(null)
                    .build()
    );
}
}