package com.example.consumerservice.kafka;

import com.example.consumerservice.model.AdjustmentSSY;
import com.example.consumerservice.model.AdjustmentSSYKafka;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class KafkaConsumerService {

    @Autowired
    private ConsumerFactory<String, AdjustmentSSYKafka> consumerFactory;

    /**
     * Fetches messages from the Kafka topic.
     *
     * @return A list of AdjustmentSSY messages.
     */
    public List<AdjustmentSSYKafka> fetchMessagesFromKafka() {
        List<AdjustmentSSYKafka> messages = new ArrayList<>();

        // Create a Kafka consumer
        try (Consumer<String, AdjustmentSSYKafka> consumer = consumerFactory.createConsumer()) {
            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList("adjustment-ssy"));

            // Poll for messages
            ConsumerRecords<String, AdjustmentSSYKafka> records = consumer.poll(Duration.ofSeconds(5));

            // Process the records
            for (ConsumerRecord<String, AdjustmentSSYKafka> record : records) {
                AdjustmentSSYKafka adjustmentSSY = record.value();

                // Convert to AdjustmentSSY (JPA entity)
                //AdjustmentSSY adjustmentSSY = AdjustmentSSY.fromKafkaModel(adjustmentSSYKafka);
                messages.add(adjustmentSSY);
            }

            // Commit the offsets
            consumer.commitSync();
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        return messages;
    }
}
