package com.hotpot.deliveryapplication.mapper;

import org.springframework.stereotype.Component;

import com.hotpot.deliveryapplication.dto.FeedBackResponse;
import com.hotpot.deliveryapplication.dto.FeedbackRequest;
import com.hotpot.deliveryapplication.model.FeedBack;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.User;

@Component
public class FeedbackMapper {

    public FeedBack toEntity(FeedbackRequest request, User user, Order order) {

        if (request == null) {
            return null;
        }

        return FeedBack.builder().restaurantRating(request.getRestaurantRating()).deliveryRating(request.getDeliveryRating())
               .overallRating(request.getOverallRating()).comment(request.getComment())
                .user(user).order(order).build();
    }

  
    public FeedBackResponse toResponse(FeedBack fb) {

        if (fb == null) {
            return null;
        }

        Integer userId = fb.getUser() != null ? fb.getUser().getUserId() : null;
        Integer orderId = fb.getOrder() != null ? fb.getOrder().getOrderId() : null;

        return FeedBackResponse.builder().feedbackId(fb.getFeedbackId()).userId(userId).orderId(orderId)
                .restaurantRating(fb.getRestaurantRating()).deliveryRating(fb.getDeliveryRating()).overallRating(fb.getOverallRating())
                .comment(fb.getComment()).build();
    }
}