package com.hotpot.deliveryapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.exception.BadRequestException;
import com.hotpot.deliveryapplication.exception.ResourceNotFoundException;
import com.hotpot.deliveryapplication.exception.UnauthorizedException;
import com.hotpot.deliveryapplication.model.Cart;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.User;
import com.hotpot.deliveryapplication.model.User.Role;
import com.hotpot.deliveryapplication.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo ur;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    
    public User register(User user) {

        if (ur.findByEmail(user.getEmail()) != null) {
            throw new BadRequestException("Email already exists");
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new BadRequestException("Password must be at least 6 characters");
        }

       if (user.getRole() == null) {
        user.setRole(Role.USER);
       }
        user.setPassword(encoder.encode(user.getPassword()));

        Cart cart = new Cart();
        cart.setUser(user);
        user.setCart(cart);

        return ur.save(user);
    }


    public User login(String email, String password) {

        User user = ur.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        if (!encoder.matches(password, user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return user;
    }

    
    public List<User> getAllUsers() {
        return ur.findAll();
    }

   
    public User getById(int id) {
        return ur.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    
    public User updateProfile(int id, User updated) {

        User user = getById(id);

        if (updated.getName() != null) {
            user.setName(updated.getName());
        }

        if (updated.getContactNumber() != null) {
            user.setContactNumber(updated.getContactNumber());
        }

        if (updated.getAddress() != null) {
            user.setAddress(updated.getAddress());
        }

        return ur.save(user);
    }

    
    public User updatePassword(int id, String password) {

        User user = getById(id);

        if (password == null || password.length() < 6) {
            throw new BadRequestException("Invalid password");
        }

        user.setPassword(encoder.encode(password));

        return ur.save(user);
    }

    
    public String deleteUser(int id) {

        if (!ur.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }

        ur.deleteById(id);
        return "User deleted successfully";
    }

   
    public int getTotalOrders(int userId) {
        User user = getById(userId);
        return user.getOrders() != null ? user.getOrders().size() : 0;
    }

   
    public double getTotalSpent(int userId) {

        User user = getById(userId);

        if (user.getOrders() == null) {
            return 0;
        }

        double total = 0;

        for (Order order : user.getOrders()) {
            total += order.getTotalAmount();
        }

        return total;
    }
}