package com.hotpot.deliveryapplication.mapper;

import com.hotpot.deliveryapplication.dto.LoginRequest;

public class LoginMapper {

    public static String getEmail(LoginRequest request) {
        return request.getEmail();
    }

    public static String getPassword(LoginRequest request) {
        return request.getPassword();
    }
}
