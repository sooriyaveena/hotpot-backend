package com.hotpot.deliveryapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.model.FeedBack;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.User;
import com.hotpot.deliveryapplication.repository.FeedBackRepo;
import com.hotpot.deliveryapplication.repository.OrderRepo;
import com.hotpot.deliveryapplication.repository.UserRepo;

@Service
public class FeedBackService {

    @Autowired
    private FeedBackRepo fr;

    @Autowired
    private OrderRepo or;

    @Autowired
    private UserRepo ur;
 
    public FeedBack addFeedback(int userId, int orderId, FeedBack feedback) {

        User user = ur.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = or.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        if (order.getUser().getUserId() != userId) {
            throw new RuntimeException("Unauthorized");
        }
        if (order.getStatus() != Order.Status.DELIVERED) {
            throw new RuntimeException("Cannot give feedback before delivery");
        }

        if (fr.existsByOrder_OrderId(orderId)) {
            throw new RuntimeException("Feedback already given");
        }

        feedback.setUser(user);
        feedback.setOrder(order);

        return fr.save(feedback);
    }
    public List<FeedBack> getFeedbackByRestaurant(int restaurantId) {
        return fr.findByOrder_Restaurant_RestaurantId(restaurantId);
    }
    public List<FeedBack> getFeedbackByUser(int userId) {
        return fr.findByUser_UserId(userId);
    }
}
