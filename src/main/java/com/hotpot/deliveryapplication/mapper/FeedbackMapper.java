package com.hotpot.deliveryapplication.mapper;

import org.springframework.stereotype.Component;

import com.hotpot.deliveryapplication.dto.FeedBackResponse;
import com.hotpot.deliveryapplication.dto.FeedbackRequest;
import com.hotpot.deliveryapplication.model.FeedBack;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.Restaurant;
import com.hotpot.deliveryapplication.model.User;

@Component
public class FeedbackMapper {

   
    public FeedBack toEntity(

            FeedbackRequest request,

            User user,

            Order order,

            Restaurant restaurant
    ) {

        if (request == null) {

            return null;
        }

        return FeedBack.builder()

                .rating(
                    request.getRating()
                )

                .comment(
                    request.getComment()
                )

                .user(user)

                .order(order)

                .restaurant(restaurant)

                .build();
    }

    public FeedBackResponse toResponse(
            FeedBack feedback) {

        if (feedback == null) {

            return null;
        }

        Integer userId =

                feedback.getUser() != null

                ?

                feedback.getUser().getUserId()

                :

                null;

        Integer orderId =

                feedback.getOrder() != null

                ?

                feedback.getOrder().getOrderId()

                :

                null;

        Integer restaurantId =

                feedback.getRestaurant() != null

                ?

                feedback.getRestaurant()
                        .getRestaurantId()

                :

                null;

        return FeedBackResponse.builder()

                .feedbackId(
                    feedback.getFeedbackId()
                )

                .userId(userId)

                .orderId(orderId)

                .restaurantId(restaurantId)

                .rating(
                    feedback.getRating()
                )

                .comment(
                    feedback.getComment()
                )

                .build();
    }

}