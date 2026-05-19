package com.hotpot.deliveryapplication.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.model.DeliveryPerson;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.DeliveryPersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryPersonController {

    private final DeliveryPersonService service;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<DeliveryPerson>>> getAll() {
        List<DeliveryPerson> list = service.getAll();

        return ResponseEntity.ok(
                ApiResponse.<List<DeliveryPerson>>builder()
                        .success(true)
                        .message("Delivery persons fetched successfully")
                        .data(list)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryPerson>> getById(@PathVariable int id) {
        DeliveryPerson dp = service.getById(id);

        return ResponseEntity.ok(
                ApiResponse.<DeliveryPerson>builder()
                        .success(true)
                        .message("Delivery person fetched successfully")
                        .data(dp)
                        .build()
        );
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<DeliveryPerson>> save(@RequestBody DeliveryPerson d) {
        DeliveryPerson saved = service.save(d);

        return ResponseEntity.ok(
                ApiResponse.<DeliveryPerson>builder()
                        .success(true)
                        .message("Delivery person saved successfully")
                        .data(saved)
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
public ResponseEntity<ApiResponse<String>> delete(
        @PathVariable int id
) {

    service.delete(id);

    return ResponseEntity.ok(

            ApiResponse.<String>builder()

                    .success(true)

                    .message("Delivery person deleted successfully")

                    .data("Deleted")

                    .build()
    );
}
}