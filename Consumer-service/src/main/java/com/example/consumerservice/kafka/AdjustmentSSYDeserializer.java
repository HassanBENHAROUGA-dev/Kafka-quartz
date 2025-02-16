package com.example.consumerservice.kafka;

import com.example.consumerservice.model.AdjustmentSSYKafka;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;


public class AdjustmentSSYDeserializer implements Deserializer<AdjustmentSSYKafka> {
    private final ObjectMapper objectMapper;

    public AdjustmentSSYDeserializer() {
        this.objectMapper = new ObjectMapper();
        // Register the JavaTimeModule to handle Java 8 date/time types
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public AdjustmentSSYKafka deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, AdjustmentSSYKafka.class);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing message", e);
        }
    }
}
