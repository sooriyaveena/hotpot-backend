package com.hotpot.deliveryapplication.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {

    @NotNull(message = "Cart ID is required")
    @Min(value = 1, message = "Cart ID must be valid")
    private Integer cartId;

    @NotNull(message = "Menu Item ID is required")
    @Min(value = 1, message = "Menu Item ID must be valid")
    private Integer menuItemId;
}
