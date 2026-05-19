package com.hotpot.deliveryapplication.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hotpot.deliveryapplication.dto.CartItemResponse;
import com.hotpot.deliveryapplication.dto.CartResponse;
import com.hotpot.deliveryapplication.model.Cart;
import com.hotpot.deliveryapplication.model.CartItem;

@Component
public class CartMapper {

    public CartResponse toCartResponse(Cart cart) {

        if (cart == null) {
            return null;
        }

        List<CartItemResponse> items = (cart.getCartItems() == null)
                ? Collections.emptyList()
                : cart.getCartItems()
                      .stream()
                      .map(this::toCartItemResponse)
                      .collect(Collectors.toList());

        return CartResponse.builder()
                .cartId(cart.getCartId())
                .userId(cart.getUser() != null ? cart.getUser().getUserId() : 0)
                .items(items)
                .build();
    }

    private CartItemResponse toCartItemResponse(CartItem ci) {
        return CartItemResponse.builder()
                .itemId(ci.getMenuItem() != null ? ci.getMenuItem().getItemId() : 0)
                .itemName(ci.getMenuItem() != null ? ci.getMenuItem().getName() : null)
                .quantity(ci.getQuantity())
                .price(ci.getMenuItem() != null ? ci.getMenuItem().getPrice() : 0.0)
                .build();
    }
}