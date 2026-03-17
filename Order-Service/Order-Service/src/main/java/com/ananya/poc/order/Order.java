package com.ananya.poc.order;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    public enum OrderStatus {
        PENDING, CONFIRMED, FAILED
    }
}