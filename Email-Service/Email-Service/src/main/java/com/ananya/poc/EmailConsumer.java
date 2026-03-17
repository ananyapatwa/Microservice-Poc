package com.ananya.poc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailConsumer {
    @KafkaListener(topics = "order-confirmed", groupId = "email-service-group")
    public void handleOrderConfirmed(OrderConfirmedEvent event) {
        // In a real system you'd use JavaMail or SendGrid here
        log.info("=== EMAIL NOTIFICATION ===");
        log.info("To: {}", event.getUserEmail());
        log.info("Subject: Your order #{} has been confirmed!", event.getOrderId());
        log.info("Body: Hi! Your order of ${} has been successfully placed.", event.getAmount());
        log.info("==========================");
    }
}
