package com.hotpot.deliveryapplication.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeedBackResponse {
    private int feedbackId;
    private int userId;
    private int orderId;
    private double restaurantRating;
    private double deliveryRating;
    private double overallRating;
    private String comment;
}
