package com.hotpot.deliveryapplication.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class DeliveryPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryId;
    @NotBlank(message = "Name is required")
    private String name;
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phone;
    private String vehicleNumber;

    @OneToMany(mappedBy = "deliveryPerson")
    @SuppressWarnings("unused")
    private List<Order> orders;

     private boolean available;
    
}
