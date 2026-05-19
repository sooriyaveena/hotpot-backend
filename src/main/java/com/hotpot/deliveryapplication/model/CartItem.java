package com.hotpot.deliveryapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
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
public class CartItem {
    
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer cartItemId;
        @Min(value = 1, message = "Quantity must be at least 1")
        private int quantity;
    
        @ManyToOne
        @JoinColumn(name = "cart_id")
        @JsonIgnore
        private Cart cart;
    
        @ManyToOne
        @JoinColumn(name = "item_id")
        private MenuItem menuItem;
    
        
    }


