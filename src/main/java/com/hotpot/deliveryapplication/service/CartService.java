package com.hotpot.deliveryapplication.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotpot.deliveryapplication.dto.CartResponse;
import com.hotpot.deliveryapplication.exception.BadRequestException;
import com.hotpot.deliveryapplication.exception.ResourceNotFoundException;
import com.hotpot.deliveryapplication.mapper.CartMapper;
import com.hotpot.deliveryapplication.model.Cart;
import com.hotpot.deliveryapplication.model.CartItem;
import com.hotpot.deliveryapplication.model.MenuItem;
import com.hotpot.deliveryapplication.model.User;
import com.hotpot.deliveryapplication.repository.CartRepo;
import com.hotpot.deliveryapplication.repository.MenuItemRepo;
import com.hotpot.deliveryapplication.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final UserRepo ur;

    private final MenuItemRepo mr;

    private final CartRepo cr;

    private final CartMapper mapper;

    private Cart getOrCreateCart(int userId) {

        User user = ur.findById(userId)

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "User not found"
                        )
                );

        Cart cart = user.getCart();

        if (cart == null) {

            cart = Cart.builder()

                    .user(user)

                    .cartItems(new ArrayList<>())

                    .build();

            cr.save(cart);

        }

        if (cart.getCartItems() == null) {

            cart.setCartItems(
                    new ArrayList<>()
            );

        }

        return cart;

    }

    public CartResponse getCartByUser(int userId) {

        Cart cart =
                getOrCreateCart(userId);

        return mapper.toCartResponse(cart);

    }

    public CartResponse addItemToCart(

            int userId,

            int itemId,

            int quantity

    ) {

        if (quantity <= 0) {

            throw new BadRequestException(
                    "Quantity must be positive"
            );

        }

        Cart cart =
                getOrCreateCart(userId);

        MenuItem item = mr.findById(itemId)

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Item not found"
                        )
                );

        cart.getCartItems()

                .stream()

                .filter(ci ->

                        ci.getMenuItem()
                                .getItemId() == itemId
                )

                .findFirst()

                .ifPresentOrElse(

                        ci -> {

                            ci.setQuantity(

                                    ci.getQuantity()
                                            + quantity

                            );

                        },

                        () -> {

                            CartItem newItem =

                                    CartItem.builder()

                                            .cart(cart)

                                            .menuItem(item)

                                            .quantity(quantity)

                                            .build();

                            cart.getCartItems()
                                    .add(newItem);

                        }

                );

        return mapper.toCartResponse(
                cr.save(cart)
        );

    }

    public CartResponse updateItemQuantity(

            int userId,

            int itemId,

            int change

    ) {

        Cart cart =
                getOrCreateCart(userId);

        CartItem ci = cart.getCartItems()

                .stream()

                .filter(item ->

                        item.getMenuItem()
                                .getItemId() == itemId
                )

                .findFirst()

                .orElseThrow(() ->

                        new ResourceNotFoundException(
                                "Item not found in cart"
                        )
                );

        int newQty =
                ci.getQuantity() + change;

        if (newQty <= 0) {

            cart.getCartItems()
                    .remove(ci);

        }

        else {

            ci.setQuantity(newQty);

        }

        return mapper.toCartResponse(
                cr.save(cart)
        );

    }

    public CartResponse removeItem(

            int userId,

            int itemId

    ) {

        Cart cart =
                getOrCreateCart(userId);

        cart.getCartItems()

                .removeIf(ci ->

                        ci.getMenuItem()
                                .getItemId() == itemId

                );

        return mapper.toCartResponse(
                cr.save(cart)
        );

    }

    public CartResponse clearCart(int userId) {

        Cart cart =
                getOrCreateCart(userId);

        cart.getCartItems().clear();

        return mapper.toCartResponse(
                cr.save(cart)
        );

    }

}