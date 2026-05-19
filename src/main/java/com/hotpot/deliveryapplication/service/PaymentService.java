package com.hotpot.deliveryapplication.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.exception.BadRequestException;
import com.hotpot.deliveryapplication.exception.ResourceNotFoundException;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.Payment;
import com.hotpot.deliveryapplication.model.Payment.PaymentMethod;
import com.hotpot.deliveryapplication.model.Payment.Status;
import com.hotpot.deliveryapplication.repository.OrderRepo;
import com.hotpot.deliveryapplication.repository.PaymentRepo;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepo pr;

    @Autowired
    private OrderRepo or;

    public Payment createPayment(int orderId, PaymentMethod method) {

        Order order = or.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (pr.existsByOrder_OrderId(orderId)) {
            throw new BadRequestException("Payment already exists for this order");
        }

        Payment payment = Payment.builder().order(order).amount(order.getTotalAmount()).paymentMethod(method).status(Status.PENDING)
                .transactionId(generateTransactionId()).paidAt(LocalDate.now()).build();
        return pr.save(payment);
    }

    public Payment processPayment(int paymentId, boolean success) {

        Payment payment = pr.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        if (payment.getStatus() != Status.PENDING) {
            throw new RuntimeException("Payment already processed");
        }

        payment.setStatus(success ? Status.SUCCESS : Status.FAILED);

        return pr.save(payment);
    }

    public Payment getPaymentByOrder(int orderId) {
        return pr.findByOrder_OrderId(orderId);
    }

    private String generateTransactionId() {
        return "TXN-" + UUID.randomUUID();
    }
}