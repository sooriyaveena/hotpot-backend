package com.hotpot.deliveryapplication.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    @NotNull(message = "User ID is required")
    @Min(value = 1, message = "User ID must be valid")
    private Integer userId;

    @NotNull(message = "Cart ID is required")
    @Min(value = 1, message = "Cart ID must be valid")
    private Integer cartId;

    @NotBlank(message = "Shipping address cannot be empty")
    @Size(max = 500, message = "Address too long")
    private String address;
}
