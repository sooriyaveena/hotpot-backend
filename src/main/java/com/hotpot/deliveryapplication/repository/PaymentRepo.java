package com.hotpot.deliveryapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>{
    public boolean existsByOrder_OrderId(int orderId);
    public Payment findByOrder_OrderId(int orderId);
}
