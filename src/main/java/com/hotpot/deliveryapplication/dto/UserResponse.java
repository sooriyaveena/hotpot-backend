package com.hotpot.deliveryapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private int userId;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private String role;
}