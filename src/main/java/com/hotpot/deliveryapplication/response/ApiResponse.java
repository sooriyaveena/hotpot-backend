package com.hotpot.deliveryapplication.response;

import com.hotpot.deliveryapplication.model.Category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
}