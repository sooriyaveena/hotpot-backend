package com.hotpot.deliveryapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor 
@AllArgsConstructor
public class RestaurantResponse {

    private int restaurantId;

    private String name;

    private String address;

    private String categories;

    private double rating;
}

