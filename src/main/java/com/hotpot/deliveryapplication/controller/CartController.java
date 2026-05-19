package com.hotpot.deliveryapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.dto.CartResponse;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cs;


    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> getCart(@PathVariable int userId) {

        CartResponse cart = cs.getCartByUser(userId);

        return ResponseEntity.ok(ApiResponse.<CartResponse>builder().success(true).message("Cart fetched successfully")
                        .data(cart).build());
    }

   
    @PostMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponse<CartResponse>> addItem(@PathVariable int userId,@PathVariable int itemId,@RequestParam int quantity) {

        CartResponse cart = cs.addItemToCart(userId, itemId, quantity);

        return ResponseEntity.ok(ApiResponse.<CartResponse>builder().success(true).message("Item added to cart")
        .data(cart).build());
    }

    @PutMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponse<CartResponse>> updateItem(@PathVariable int userId,@PathVariable int itemId,@RequestParam int change) {

        CartResponse cart = cs.updateItemQuantity(userId, itemId, change);

        return ResponseEntity.ok(ApiResponse.<CartResponse>builder().success(true).message("Item updated")
                        .data(cart).build());
    }

   
    @DeleteMapping("/{userId}/items/{itemId}")
    public ResponseEntity<ApiResponse<CartResponse>> removeItem(@PathVariable int userId,@PathVariable int itemId) {

        CartResponse cart = cs.removeItem(userId, itemId);

        return ResponseEntity.ok(
                ApiResponse.<CartResponse>builder().success(true).message("Item removed")
                        .data(cart).build());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<CartResponse>> clearCart(@PathVariable int userId) {
        CartResponse cart = cs.clearCart(userId);
        return ResponseEntity.ok(
                ApiResponse.<CartResponse>builder().success(true).message("Cart cleared successfully")
                        .data(cart).build());
    }

}