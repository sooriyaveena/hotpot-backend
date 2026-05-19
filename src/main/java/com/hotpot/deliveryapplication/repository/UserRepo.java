package com.hotpot.deliveryapplication.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.User;

public interface UserRepo extends JpaRepository<User, Integer>{

    public User findByName(String name);
    public User findByEmail(String email);
    
}
