package com.ananya.poc.payment;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "payments")
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    public enum PaymentStatus {
        SUCCESS, FAILURE
    }
}
