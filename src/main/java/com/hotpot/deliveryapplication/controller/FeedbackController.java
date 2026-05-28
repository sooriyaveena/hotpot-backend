package com.hotpot.deliveryapplication.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hotpot.deliveryapplication.dto.FeedbackRequest;
import com.hotpot.deliveryapplication.model.FeedBack;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.FeedBackService;

class FeedbackController{
    private final FeedBackService feedbackService = null;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<FeedBack>>
    addFeedback(

            @RequestBody
            FeedbackRequest request) {

        FeedBack feedback =
                feedbackService
                .addFeedback(request);

        return ResponseEntity.ok(

                ApiResponse
                .<FeedBack>builder()

                .success(true)

                .message(
                    "Feedback submitted successfully"
                )

                .data(feedback)

                .build()
        );
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<ApiResponse<List<FeedBack>>>
    getRestaurantFeedbacks(
            @PathVariable int id) {

        List<FeedBack> feedbacks =

                feedbackService
                .getRestaurantFeedbacks(id);

        return ResponseEntity.ok(

                ApiResponse
                .<List<FeedBack>>builder()

                .success(true)

                .message(
                    "Restaurant feedbacks fetched"
                )

                .data(feedbacks)

                .build()
        );
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<FeedBack>>>
    getAll() {

        return ResponseEntity.ok(

                ApiResponse
                .<List<FeedBack>>builder()

                .success(true)

                .message(
                    "All feedbacks fetched"
                )

                .data(
                    feedbackService.getAll()
                )

                .build()
        );
    }

}

