package com.hotpot.deliveryapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.FeedBack;

public interface FeedBackRepo extends JpaRepository<FeedBack, Integer> {

    boolean existsByOrder_OrderId(int orderId);

    List<FeedBack> findByOrder_Restaurant_RestaurantId(int restaurantId);

    List<FeedBack> findByUser_UserId(int userId);
    List<FeedBack>
    findByRestaurantRestaurantId(
            int restaurantId
    );
}