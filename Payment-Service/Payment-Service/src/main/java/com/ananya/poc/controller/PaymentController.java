package com.ananya.poc.controller;

import com.ananya.poc.payment.Payment;
import com.ananya.poc.service.PaymentService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestBody PaymentRequest request) {
        Payment payment = paymentService.processPayment(
                request.getOrderId(), request.getAmount());
        return ResponseEntity.ok(payment);
    }
    // Inner DTO — keeps it simple for a POC
    @Data
    public static class PaymentRequest {
        private Long orderId;
        private Double amount;
    }
}