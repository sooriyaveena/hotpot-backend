package com.hotpot.deliveryapplication.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.model.OrderItem;
import com.hotpot.deliveryapplication.repository.OrderItemRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepo repo;

    public OrderItem save(OrderItem item) {
        return repo.save(item);
    }

    public List<OrderItem> getItemsByOrder(int orderId) {
        return repo.findByOrderOrderId(orderId);
    }

    public double calculateOrderTotal(int orderId) {
        return repo.findByOrderOrderId(orderId)
                .stream()
                .mapToDouble(i -> i.getMenuItem().getPrice() * i.getQuantity())
                .sum();
    }
}

