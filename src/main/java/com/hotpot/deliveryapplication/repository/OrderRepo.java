package com.hotpot.deliveryapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{

    public List<Order> findByUser_UserId(int userId);
    
}
