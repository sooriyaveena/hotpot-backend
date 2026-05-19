package com.hotpot.deliveryapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50)
    private String name;

    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$",
             message = "Invalid phone number")
    private String contactNumber;

    @Email(message = "Invalid email")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(max = 255)
    private String address;

    private String role;
}