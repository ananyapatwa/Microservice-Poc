package com.ananya.poc.controller;
import com.ananya.poc.order.Order;
import com.ananya.poc.service.OrderService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class Controller {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        Order order = orderService.createOrder(
                request.getUserId(),
                request.getAmount(),
                request.getUserEmail()
        );
        return ResponseEntity.ok(order);
    }
    @Data
    public static class OrderRequest {
        private Long userId;
        private Double amount;
        private String userEmail;
    }
}