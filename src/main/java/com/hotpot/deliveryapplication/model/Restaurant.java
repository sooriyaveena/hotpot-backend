package com.hotpot.deliveryapplication.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int restaurantId;

    @NotBlank(message = "Restaurant name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String contactNumber;

    @Email(message = "Invalid email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
@OneToMany(
    mappedBy = "restaurant",
    cascade = CascadeType.ALL,
    orphanRemoval = true
)
private List<MenuItem> menuItems;


      @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Category> categories;

    private String image;
}