package  com.hotpot.deliveryapplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotpot.deliveryapplication.model.Payment;
import com.hotpot.deliveryapplication.model.Payment.PaymentMethod;
import com.hotpot.deliveryapplication.response.ApiResponse;
import com.hotpot.deliveryapplication.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

   private final PaymentService ps;

    
    @PostMapping("/createpayment/{orderId}")
    public ResponseEntity<ApiResponse<Payment>> createPayment(@PathVariable int orderId,@RequestBody PaymentMethod method) {

        Payment payment = ps.createPayment(orderId, method);

        return ResponseEntity.ok(ApiResponse.<Payment>builder().success(true).message("Payment created successfully")
                        .data(payment).build());
    }
    
    @PutMapping("/processpayment/{paymentId}")
    public ResponseEntity<ApiResponse<Payment>> processPayment(@PathVariable int paymentId,@RequestParam boolean success) {

        Payment payment = ps.processPayment(paymentId, success);

        return ResponseEntity.ok(ApiResponse.<Payment>builder().success(true).message(success ? "Payment successful" : "Payment failed")
                        .data(payment).build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse<Payment>> getPaymentByOrderId(@PathVariable int orderId) {

        Payment payment = ps.getPaymentByOrder(orderId);

        return ResponseEntity.ok(ApiResponse.<Payment>builder().success(true).message("Payment fetched successfully")
                        .data(payment).build());
    }

}