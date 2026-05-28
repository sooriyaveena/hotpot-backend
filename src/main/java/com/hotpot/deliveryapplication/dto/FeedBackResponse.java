package com.hotpot.deliveryapplication.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FeedBackResponse {
      private int feedbackId;

    private Integer userId;

    private Integer orderId;

    private Integer restaurantId;

    private int rating;

    private String comment;
}
