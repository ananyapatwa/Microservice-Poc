package com.ananya.poc.Repo;

import com.ananya.poc.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
