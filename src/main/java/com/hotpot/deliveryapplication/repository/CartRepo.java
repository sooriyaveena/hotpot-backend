package com.hotpot.deliveryapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer>{
    
}
