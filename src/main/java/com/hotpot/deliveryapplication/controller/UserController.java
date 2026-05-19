package com.hotpot.deliveryapplication.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.configuration.JwtUtil;
import com.hotpot.deliveryapplication.dto.LoginRequest;
import com.hotpot.deliveryapplication.dto.RegisterRequest;
import com.hotpot.deliveryapplication.dto.UserResponse;
import com.hotpot.deliveryapplication.mapper.UserMapper;
import com.hotpot.deliveryapplication.model.User;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {

        User user = UserMapper.toEntity(request);
        User saved = userService.register(user);

        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().success(true)
                        .message("User registered successfully").data(UserMapper.toDTO(saved)).build()
        );
    }

  
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(
            @Valid @RequestBody LoginRequest request) {

        User user = userService.login(request.getEmail(), request.getPassword());

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", UserMapper.toDTO(user));

        return ResponseEntity.ok(
                ApiResponse.<Map<String, Object>>builder()
                        .success(true)
                        .message("Login successful")
                        .data(response)
                        .build()
        );
    }

    @GetMapping("/getallusers")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {

        List<UserResponse> users = userService.getAllUsers()
                .stream()
                .map(UserMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                ApiResponse.<List<UserResponse>>builder()
                        .success(true)
                        .message("Users fetched successfully")
                        .data(users)
                        .build()
        );
    }

  
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable int id) {

        User user = userService.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message("User fetched successfully")
                        .data(UserMapper.toDTO(user))
                        .build()
        );
    }


    @PutMapping("/updateprofile/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateProfile(
            @PathVariable int id,
            @Valid @RequestBody RegisterRequest request) {

        User updatedUser = UserMapper.toEntity(request);
        User user = userService.updateProfile(id, updatedUser);

        return ResponseEntity.ok(
                ApiResponse.<UserResponse>builder()
                        .success(true)
                        .message("Profile updated successfully")
                        .data(UserMapper.toDTO(user))
                        .build()
        );
    }

    @PutMapping("/updatepassword/{id}/password")
    public ResponseEntity<ApiResponse<String>> updatePassword(
            @PathVariable int id,
            @RequestParam String password) {

        userService.updatePassword(id, password);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message("Password updated successfully")
                        .data(null)
                        .build()
        );
    }

    
    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable int id) {

        String msg = userService.deleteUser(id);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .success(true)
                        .message(msg)
                        .data(null)
                        .build()
        );
    }


    @GetMapping("/orders/count/{id}")
    public ResponseEntity<ApiResponse<Integer>> getTotalOrders(@PathVariable int id) {

        int count = userService.getTotalOrders(id);

        return ResponseEntity.ok(
                ApiResponse.<Integer>builder()
                        .success(true)
                        .message("Total orders fetched")
                        .data(count)
                        .build()
        );
    }

  
    @GetMapping("/spent/{id}")
    public ResponseEntity<ApiResponse<Double>> getTotalSpent(@PathVariable int id) {

        double total = userService.getTotalSpent(id);

        return ResponseEntity.ok(
                ApiResponse.<Double>builder()
                        .success(true)
                        .message("Total spent fetched")
                        .data(total)
                        .build()
        );
    }
}