package com.hotpot.deliveryapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequest {
    
    private int userId;

    private int restaurantId;

    private int orderId;

    private int rating;

    private String comment;
}
