package com.hotpot.deliveryapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.model.OrderItem;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.OrderItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService service;

    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<List<OrderItem>>> getItems(@PathVariable int orderId) {
        List<OrderItem> items = service.getItemsByOrder(orderId);

        return ResponseEntity.ok(
                ApiResponse.<List<OrderItem>>builder()
                        .success(true)
                        .message("Order items fetched successfully")
                        .data(items)
                        .build()
        );
    }

    @GetMapping("/total/{orderId}")
    public ResponseEntity<ApiResponse<Double>> getTotal(@PathVariable int orderId) {
        double total = service.calculateOrderTotal(orderId);

        return ResponseEntity.ok(
                ApiResponse.<Double>builder()
                        .success(true)
                        .message("Order total calculated")
                        .data(total)
                        .build()
        );
    }
}