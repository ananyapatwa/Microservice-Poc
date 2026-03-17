package com.ananya.poc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmedEvent {
    private Long orderId;
    private Long userId;
    private Double amount;
    private String userEmail;
}