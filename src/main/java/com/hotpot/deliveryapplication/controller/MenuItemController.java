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

import com.hotpot.deliveryapplication.model.MenuItem;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.MenuItemService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuItemController {

    private final MenuItemService service;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<MenuItem>>> getAll() {
        List<MenuItem> items = service.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<MenuItem>>builder()
                        .success(true)
                        .message("Menu items fetched successfully")
                        .data(items)
                        .build()
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<MenuItem>>> filter(
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Boolean isVeg,
            @RequestParam(required = false) String keyword) {

        List<MenuItem> items = service.filterItems(categoryId, isVeg, keyword);

        return ResponseEntity.ok(
                ApiResponse.<List<MenuItem>>builder()
                        .success(true)
                        .message("Filtered menu items fetched")
                        .data(items)
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<MenuItem>> save(@RequestBody MenuItem item) {
        MenuItem saved = service.save(item);

        return ResponseEntity.ok(
                ApiResponse.<MenuItem>builder()
                        .success(true)
                        .message("Menu item saved successfully")
                        .data(saved)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
public ResponseEntity<ApiResponse<String>> delete(
        @PathVariable int id) {

    service.deleteMenuItem(id);

    return ResponseEntity.ok(

            ApiResponse.<String>builder()
                    .success(true)
                    .message("Menu item deleted successfully")
                    .data(null)
                    .build()
    );
}
}