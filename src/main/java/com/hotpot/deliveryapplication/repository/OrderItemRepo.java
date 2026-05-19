package com.hotpot.deliveryapplication.repository;


    import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.OrderItem;


public interface OrderItemRepo extends JpaRepository<OrderItem, Integer> {

	List<OrderItem> findByOrderOrderId(int orderId);
 

}

