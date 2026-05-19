package com.hotpot.deliveryapplication.mapper;

import com.hotpot.deliveryapplication.dto.RegisterRequest;
import com.hotpot.deliveryapplication.dto.UserResponse;
import com.hotpot.deliveryapplication.model.User;

public class UserMapper {

   
   public static User toEntity(RegisterRequest dto) {
    if (dto == null) return null;

    User user = new User();

    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setContactNumber(dto.getContactNumber());
    user.setAddress(dto.getAddress());
    user.setPassword(dto.getPassword());

    if (dto.getRole() != null) {
        user.setRole(User.Role.valueOf(dto.getRole().toUpperCase()));
    }

    return user;
}

    public static UserResponse toDTO(User user) {
        if (user == null) return null;

        UserResponse dto = new UserResponse();

        dto.setUserId(user.getUserId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setContactNumber(user.getContactNumber());
        dto.setAddress(user.getAddress());

        if (user.getRole() != null) {
            dto.setRole(user.getRole().name());
        }

        return dto;
    }


    public static void updateEntity(User user, RegisterRequest dto) {
        if (user == null || dto == null) return;

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        if (dto.getContactNumber() != null) {
            user.setContactNumber(dto.getContactNumber());
        }

        if (dto.getAddress() != null) {
            user.setAddress(dto.getAddress());
        }

    
}
}