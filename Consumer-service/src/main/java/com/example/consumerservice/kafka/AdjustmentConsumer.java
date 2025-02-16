package com.example.consumerservice.kafka;

import com.example.consumerservice.model.AdjustmentSSY;
import com.example.consumerservice.model.AdjustmentSSYKafka;
import com.example.consumerservice.repository.AdjustmentSSYRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AdjustmentConsumer {
    private static final Logger logger = LoggerFactory.getLogger(AdjustmentConsumer.class);

    @Autowired
    private AdjustmentSSYRepository adjustmentSSYRepository;

    @KafkaListener(topics = "adjustment-ssy", groupId = "adjustment-group")
    public void consume(AdjustmentSSYKafka adjustmentSSYKafka) {
        logger.info("Received adjustment: {}", adjustmentSSYKafka);

        // Save the adjustment to the database
        AdjustmentSSY adjustmentSSY = new AdjustmentSSY();
        adjustmentSSY = AdjustmentSSY.fromKafkaModel(adjustmentSSYKafka);
        adjustmentSSYRepository.save(adjustmentSSY);
        logger.info("Adjustment saved to database with ID: {}", adjustmentSSY.getIdJob());
    }

}
