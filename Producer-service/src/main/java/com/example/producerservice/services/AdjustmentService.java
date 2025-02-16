package com.example.producerservice.services;

import com.example.producerservice.model.AdjustmentSSY;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

@Service
public class AdjustmentService {

    private static final Logger logger = LoggerFactory.getLogger(AdjustmentService.class);

    private final KafkaTemplate<String, AdjustmentSSY> kafkaTemplate;

    public AdjustmentService(KafkaTemplate<String, AdjustmentSSY> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAdjustment(AdjustmentSSY adjustmentSSY) {
        logger.info("Sending adjustment to Kafka: {}", adjustmentSSY);

        // Send the message and get a CompletableFuture
        CompletableFuture<SendResult<String, AdjustmentSSY>> future = kafkaTemplate.send("adjustment-ssy", adjustmentSSY);

        // Handle the result or exception
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                logger.info("Message sent successfully: {}", result);
            } else {
                logger.error("Failed to send message", ex);
            }
        });
    }
}
