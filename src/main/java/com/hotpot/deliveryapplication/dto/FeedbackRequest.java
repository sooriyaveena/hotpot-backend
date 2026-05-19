package com.hotpot.deliveryapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {
    private double restaurantRating;
    private double deliveryRating;
    private double overallRating;
    private String comment;
}
