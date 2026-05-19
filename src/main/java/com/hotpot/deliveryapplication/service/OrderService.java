package com.hotpot.deliveryapplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.exception.BadRequestException;
import com.hotpot.deliveryapplication.exception.ResourceNotFoundException;
import com.hotpot.deliveryapplication.exception.UnauthorizedException;
import com.hotpot.deliveryapplication.model.Cart;
import com.hotpot.deliveryapplication.model.CartItem;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.model.OrderItem;
import com.hotpot.deliveryapplication.model.User;
import com.hotpot.deliveryapplication.repository.CartRepo;
import com.hotpot.deliveryapplication.repository.OrderRepo;
import com.hotpot.deliveryapplication.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

   private final OrderRepo orderRepository;
    private final UserRepo userRepository;
    private final CartRepo cartRepository;
    private final DeliveryPersonService deliveryPersonService;

    public Order placeOrder(int userId, int cartId, String address) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        if (cart.getUser().getUserId() != userId) {
            throw new UnauthorizedException("Cart does not belong to user");
        }

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems == null || cartItems.isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setRestaurant(cartItems.get(0).getMenuItem().getRestaurant());
        order.setShippingAddress(address);
        order.setStatus(Order.Status.PLACED);
        order.setPaymentMethod(Order.PaymentMethod.CASH_ON_DELIVERY);

        double total = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem c : cartItems) {

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setMenuItem(c.getMenuItem());
            oi.setQuantity(c.getQuantity());

            total += c.getMenuItem().getPrice() * c.getQuantity();

            orderItems.add(oi);
        }

        order.setTotalAmount(total);
        order.setDiscountApplied(0);
        order.setOrderItems(orderItems);

        deliveryPersonService.assignDeliveryPerson(order);

        Order savedOrder = orderRepository.save(order);

        cart.getCartItems().clear();

        return savedOrder;
    }

    public Order cancelOrder(int orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (order.getStatus() == Order.Status.DELIVERED) {
            throw new BadRequestException("Cannot cancel delivered order");
        }

        order.setStatus(Order.Status.CANCELLED);

        if (order.getDeliveryPerson() != null) {
            order.getDeliveryPerson().setAvailable(true);
        }

        return orderRepository.save(order);
    }

    public Order getById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order updateStatus(int orderId, Order.Status newStatus) {

    Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

    Order.Status currentStatus = order.getStatus();

    if (currentStatus == Order.Status.DELIVERED || currentStatus == Order.Status.CANCELLED) {
        throw new BadRequestException("Order cannot be updated");
    }

    if (currentStatus == Order.Status.PLACED) {
        if (newStatus != Order.Status.PREPARING && newStatus != Order.Status.CANCELLED) {
            throw new BadRequestException("Invalid status transition from PLACED");
        }
    }

    else if (currentStatus == Order.Status.PREPARING) {
        if (newStatus != Order.Status.DELIVERED && newStatus != Order.Status.CANCELLED) {
            throw new BadRequestException("Invalid status transition from PREPARING");
        }
    }

    order.setStatus(newStatus);

    if ((newStatus == Order.Status.DELIVERED || newStatus == Order.Status.CANCELLED)
            && order.getDeliveryPerson() != null) {
        order.getDeliveryPerson().setAvailable(true);
    }

    return orderRepository.save(order);
}

    
}