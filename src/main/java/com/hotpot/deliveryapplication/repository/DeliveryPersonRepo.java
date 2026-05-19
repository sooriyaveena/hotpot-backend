package com.hotpot.deliveryapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotpot.deliveryapplication.model.DeliveryPerson;


public interface DeliveryPersonRepo extends JpaRepository<DeliveryPerson, Integer> {
	List<DeliveryPerson> findByAvailableTrue();
}
