package com.hotpot.deliveryapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.dto.OrderRequest;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping("/place")
    public ResponseEntity<ApiResponse<Order>> placeOrder(@Valid @RequestBody OrderRequest request) {

        Order order = service.placeOrder(
                request.getUserId(),
                request.getCartId(),
                request.getAddress()
        );

        return ResponseEntity.ok(
                ApiResponse.<Order>builder()
                        .success(true)
                        .message("Order placed successfully")
                        .data(order)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>> getById(@PathVariable int id) {
        Order order = service.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<Order>builder()
                        .success(true)
                        .message("Order fetched successfully")
                        .data(order)
                        .build()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Order>>> getAll() {
        List<Order> list = service.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<Order>>builder()
                        .success(true)
                        .message("Orders fetched successfully")
                        .data(list)
                        .build()
        );
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse<Order>> cancel(@PathVariable int id) {
        Order order = service.cancelOrder(id);

        return ResponseEntity.ok(
                ApiResponse.<Order>builder()
                        .success(true)
                        .message("Order cancelled successfully")
                        .data(order)
                        .build()
        );
    }
    @PutMapping("/update-status/{id}")
    public ResponseEntity<ApiResponse<Order>> updateStatus(
            @PathVariable int id,
            @RequestParam Order.Status status) {

        Order order = service.updateStatus(id, status);

        return ResponseEntity.ok(
                ApiResponse.<Order>builder()
                        .success(true)
                        .message("Order status updated")
                        .data(order)
                        .build()
        );
    }
}