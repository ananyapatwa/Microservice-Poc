package com.ananya.poc.service;
import com.ananya.poc.Repo.PaymentRepository;
import com.ananya.poc.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    public Payment processPayment(Long orderId, Double amount) {
        Payment payment = new Payment();
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        // Simulate: fail if amount > 10000, succeed otherwise
        if (amount > 10000) {
            payment.setStatus(Payment.PaymentStatus.FAILURE);
        } else {
            payment.setStatus(Payment.PaymentStatus.SUCCESS);
        }
        return paymentRepository.save(payment);
    }
}
