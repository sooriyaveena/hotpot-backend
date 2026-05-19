package com.hotpot.deliveryapplication.mapper;

import com.hotpot.deliveryapplication.model.Cart;
import com.hotpot.deliveryapplication.model.CartItem;
import com.hotpot.deliveryapplication.model.MenuItem;


public class CartItemMapper {

    public static CartItem toEntity(Cart cart, MenuItem menuItem) {
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setMenuItem(menuItem);
        item.setQuantity(1);
        return item;
    }
}
