package com.hotpot.deliveryapplication.mapper;

import com.hotpot.deliveryapplication.dto.OrderRequest;
import com.hotpot.deliveryapplication.model.Cart;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.User;

public class OrderMapper {

    public static Order toEntity(OrderRequest request, User user, Cart cart) {

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(request.getAddress());
        order.setStatus(Order.Status.PLACED);

        return order;
    }
}
