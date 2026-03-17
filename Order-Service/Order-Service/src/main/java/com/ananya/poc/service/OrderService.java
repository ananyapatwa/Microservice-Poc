package com.ananya.poc.service;

import com.ananya.poc.order.Order;
import com.ananya.poc.order.OrderConfirmedEvent;
import com.ananya.poc.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderConfirmedEvent> kafkaTemplate;
    private final RestTemplate restTemplate;
    private static final String KAFKA_TOPIC = "order-confirmed";
    private static final String PAYMENT_SERVICE_URL = "http://PAYMENT-SERVICE/payments/process";
    public Order createOrder(Long userId, Double amount, String userEmail) {
        // 1. Save order with PENDING status
        Order order = new Order();
        order.setUserId(userId);
        order.setAmount(amount);
        order.setStatus(Order.OrderStatus.PENDING);
        order = orderRepository.save(order);
        // 2. Call Payment Service synchronously
        Map<String, Object> paymentRequest = Map.of(
                "orderId", order.getId(),
                "amount", amount
        );
        // PaymentResponse is a simple inner class below
        PaymentResponse paymentResponse = restTemplate.postForObject(
                PAYMENT_SERVICE_URL, paymentRequest, PaymentResponse.class);
        // 3. Update order status based on payment result
        if (paymentResponse != null && "SUCCESS".equals(paymentResponse.getStatus())) {
            order.setStatus(Order.OrderStatus.CONFIRMED);
            orderRepository.save(order);
            // 4. Publish Kafka event
            OrderConfirmedEvent event = new OrderConfirmedEvent(
                    order.getId(), userId, amount, userEmail);
            kafkaTemplate.send(KAFKA_TOPIC, event);
            log.info("Order confirmed. Event published to Kafka: {}", event);
        } else {
            order.setStatus(Order.OrderStatus.FAILED);
            orderRepository.save(order);
            log.warn("Payment failed for order id: {}", order.getId());
        }
        return order;
    }
    // Simple DTO to deserialize payment response
    @lombok.Data
    public static class PaymentResponse {
        private String status;
    }
}
