package com.hotpot.deliveryapplication.repository;


    import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.CartItem;



public interface CartItemRepo extends JpaRepository<CartItem, Integer> {

    List<CartItem> findByCartCartId(int cartId);

    Optional<CartItem> findByCartCartIdAndMenuItemItemId(int cartId, int itemId);
}

