package com.hotpot.deliveryapplication.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.dto.AddToCartRequest;
import com.hotpot.deliveryapplication.model.CartItem;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.CartItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/cart-items")
@RequiredArgsConstructor
public class CartItemController {

     private final CartItemService service;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CartItem>> addItem(
            @Valid @RequestBody AddToCartRequest request) {

        CartItem item = service.addOrUpdateItem(
                request.getCartId(),
                request.getMenuItemId()
        );

        return ResponseEntity.ok(
                ApiResponse.<CartItem>builder()
                        .success(true)
                        .message("Item added successfully")
                        .data(item)
                        .build()
        );
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<ApiResponse<List<CartItem>>> getCartItems(@PathVariable int cartId) {

        List<CartItem> items = service.getItemsByCart(cartId);

        return ResponseEntity.ok(
                ApiResponse.<List<CartItem>>builder()
                        .success(true)
                        .message("Cart items fetched successfully")
                        .data(items)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> removeItem(@PathVariable int id) {

        service.removeItem(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Item removed successfully")
                        .data(null)
                        .build()
        );
    }

    @PutMapping("/increase")
    public ResponseEntity<ApiResponse<CartItem>> increase(
            @Valid @RequestBody AddToCartRequest request) {

        CartItem item = service.increaseQuantity(
                request.getCartId(),
                request.getMenuItemId()
        );

        return ResponseEntity.ok(
                ApiResponse.<CartItem>builder()
                        .success(true)
                        .message("Item quantity increased")
                        .data(item)
                        .build()
        );
    }

    @PutMapping("/decrease")
    public ResponseEntity<ApiResponse<CartItem>> decrease(
            @Valid @RequestBody AddToCartRequest request) {

        CartItem item = service.decreaseQuantity(
                request.getCartId(),
                request.getMenuItemId()
        );

        return ResponseEntity.ok(
                ApiResponse.<CartItem>builder()
                        .success(true)
                        .message("Item quantity decreased")
                        .data(item)
                        .build()
        );
    }

    @GetMapping("/total/{cartId}")
    public ResponseEntity<ApiResponse<Double>> getTotal(@PathVariable int cartId) {

        double total = service.calculateCartTotal(cartId);

        return ResponseEntity.ok(
                ApiResponse.<Double>builder()
                        .success(true)
                        .message("Cart total calculated")
                        .data(total)
                        .build()
        );
    }
}