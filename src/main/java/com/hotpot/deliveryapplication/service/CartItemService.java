package com.hotpot.deliveryapplication.service;

import java.util.List;
    
import org.springframework.stereotype.Service;
import com.hotpot.deliveryapplication.model.*;
import com.hotpot.deliveryapplication.repository.*;

    
    
import lombok.RequiredArgsConstructor;
    
@Service
@RequiredArgsConstructor
public class CartItemService {
    
        private final CartItemRepo repo;
        private final CartRepo cartRepository;
        private final MenuItemRepo menuItemRepository;
    
        public CartItem addOrUpdateItem(int cartId, int menuItemId) {
    
            CartItem item = repo.findByCartCartIdAndMenuItemItemId(cartId, menuItemId)
                    .orElse(null);
    
            if (item != null) {
                item.setQuantity(item.getQuantity() + 1);
                return repo.save(item);
            }
    
            CartItem newItem = new CartItem();
            newItem.setQuantity(1);
    
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new RuntimeException("Cart not found"));
    
            MenuItem menuItem = menuItemRepository.findById(menuItemId)
                    .orElseThrow(() -> new RuntimeException("Menu item not found"));
    
            newItem.setCart(cart);
            newItem.setMenuItem(menuItem);
    
            return repo.save(newItem);
        }
    
        public List<CartItem> getItemsByCart(int cartId) {
            return repo.findByCartCartId(cartId);
        }
    
        public void removeItem(int id) {
            repo.deleteById(id);
        }
    
        public CartItem increaseQuantity(int cartId, int menuItemId) {
            CartItem item = repo.findByCartCartIdAndMenuItemItemId(cartId, menuItemId)
                    .orElseThrow(() -> new RuntimeException("Item not found in cart"));
    
            item.setQuantity(item.getQuantity() + 1);
            return repo.save(item);
        }
    
        public CartItem decreaseQuantity(int cartId, int menuItemId) {
            CartItem item = repo.findByCartCartIdAndMenuItemItemId(cartId, menuItemId)
                    .orElseThrow(() -> new RuntimeException("Item not found in cart"));
    
            int qty = item.getQuantity() - 1;
    
            if (qty <= 0) {
                repo.delete(item);
                return null;
            }
    
            item.setQuantity(qty);
            return repo.save(item);
        }
    
        public double getItemTotal(CartItem item) {
            return item.getMenuItem().getPrice() * item.getQuantity();
        }
    
        public double calculateCartTotal(int cartId) {
            return repo.findByCartCartId(cartId)
                    .stream()
                    .mapToDouble(i -> i.getMenuItem().getPrice() * i.getQuantity())
                    .sum();
        }
    
        public double applyCoupon(double total, String couponCode) {
    
            if (couponCode == null || couponCode.isEmpty()) return total;
    
            if (couponCode.equalsIgnoreCase("SAVE50")) return total - 50;
    
            if (couponCode.equalsIgnoreCase("SAVE10")) return total * 0.9;
    
            return total;
        }
    
        public double calculateFinalAmount(int cartId, String coupon) {
    
            double itemTotal = calculateCartTotal(cartId);
    
            double gst = itemTotal * 0.05;
            double platformFee = 9;
    
            double total = itemTotal + gst + platformFee;
    
            if (coupon != null && !coupon.isEmpty()) {
                total = applyCoupon(total, coupon);
            }
    
            return Math.max(total, 0);
        }
    }



