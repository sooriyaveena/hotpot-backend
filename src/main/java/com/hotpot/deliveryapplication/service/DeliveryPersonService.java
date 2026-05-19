package com.hotpot.deliveryapplication.service;

import lombok.RequiredArgsConstructor;

import java.util.List;
import org.springframework.stereotype.Service;

import com.hotpot.deliveryapplication.model.DeliveryPerson;
import com.hotpot.deliveryapplication.model.Order;
import com.hotpot.deliveryapplication.repository.DeliveryPersonRepo;

@Service
@RequiredArgsConstructor
public class DeliveryPersonService {

    private final DeliveryPersonRepo repo;

    public DeliveryPerson save(DeliveryPerson d) {
        return repo.save(d);
    }

    public List<DeliveryPerson> getAll() {
        return repo.findAll();
    }

    public DeliveryPerson getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery person not found"));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public DeliveryPerson getAvailableDeliveryPerson() {
        return repo.findByAvailableTrue()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No delivery person available"));
    }

    public void assignDeliveryPerson(Order order) {
        DeliveryPerson dp = getAvailableDeliveryPerson();
        dp.setAvailable(false);
        order.setDeliveryPerson(dp);
        repo.save(dp);
    }
}
