package com.hotpot.deliveryapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.dto.FeedbackRequest;
import com.hotpot.deliveryapplication.exception.ResourceNotFoundException;
import com.hotpot.deliveryapplication.model.FeedBack;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.Restaurant;
import com.hotpot.deliveryapplication.model.User;
import com.hotpot.deliveryapplication.repository.FeedBackRepo;
import com.hotpot.deliveryapplication.repository.OrderRepo;
import com.hotpot.deliveryapplication.repository.RestaurantRepo;
import com.hotpot.deliveryapplication.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedBackService {

    private final FeedBackRepo
    feedbackRepository;

    private final UserRepo
    userRepository;

    private final RestaurantRepo
    restaurantRepository;

    private final OrderRepo
    orderRepository;

    public FeedBack addFeedback(
            FeedbackRequest request) {

        User user =
                userRepository.findById(
                        request.getUserId()
                )

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        ));

        Restaurant restaurant =
                restaurantRepository.findById(
                        request.getRestaurantId()
                )

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Restaurant not found"
                        ));

        Order order =
                orderRepository.findById(
                        request.getOrderId()
                )

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"
                        ));

        FeedBack feedback =
                new FeedBack();

        feedback.setUser(user);

        feedback.setRestaurant(
                restaurant
        );

        feedback.setOrder(order);

        feedback.setRating(
                request.getRating()
        );

        feedback.setComment(
                request.getComment()
        );

        FeedBack savedFeedback =
                feedbackRepository.save(
                        feedback
                );

        double currentRating =
                restaurant.getRating();

        int totalReviews =
                restaurant.getTotalReviews();

        double updatedRating =

                (
                    (currentRating * totalReviews)

                    +

                    request.getRating()
                )

                /

                (totalReviews + 1);

        restaurant.setRating(
                updatedRating
        );

        restaurant.setTotalReviews(
                totalReviews + 1
        );

        restaurantRepository.save(
                restaurant
        );

        return savedFeedback;
    }

    public List<FeedBack>
    getRestaurantFeedbacks(
            int restaurantId) {

        return feedbackRepository
                .findByRestaurantRestaurantId(
                        restaurantId
                );
    }

    public List<FeedBack> getAll() {

        return feedbackRepository
                .findAll();
    }
}
