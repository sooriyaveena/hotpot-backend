package com.hotpot.deliveryapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({
    "hibernateLazyInitializer",
    "handler"
})
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @NotBlank(message = "Description required")
    private String description;

    @NotBlank(message = "Item name required")
    private String name;

    @Positive(message = "Price must be positive")
    private double price;

    private String availabilityStatus;

    private String dietaryType;

    private String timing;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    @JsonIgnoreProperties({
        "menuItems",
        "orders"
    })
    private Restaurant restaurant;

    

    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "category_id")
@JsonIgnoreProperties({
    "restaurant"
})
private Category category;
}