package com.example.producerservice.kafka;

import com.example.producerservice.model.AdjustmentSSY;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers:localhost:29092}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.topic:adjustment-ssy}")
    private String topicName;

    /**
     * Configures the Kafka producer properties.
     *
     * @return A map of Kafka producer configuration properties.
     */
    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        // Kafka broker address
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // Serializer for the message key
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // Serializer for the message value
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        // Number of retries for failed message sends
        props.put(ProducerConfig.RETRIES_CONFIG, 3);
        // Delay between retries (in milliseconds)
        props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 1000);
        // Maximum time to wait for metadata fetch or buffer allocation (in milliseconds)
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 60000);
        // Acknowledgment setting: "all" means the leader and all replicas must acknowledge
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        return props;
    }

    /**
     * Creates a ProducerFactory for producing Kafka messages.
     *
     * @return A ProducerFactory instance.
     */
    @Bean
    public ProducerFactory<String, AdjustmentSSY> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    /**
     * Creates a KafkaTemplate for sending messages to Kafka.
     *
     * @return A KafkaTemplate instance.
     */
    @Bean
    public KafkaTemplate<String, AdjustmentSSY> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /**
     * Creates a new Kafka topic if it doesn't already exist.
     *
     * @return A NewTopic instance representing the Kafka topic.
     */
    @Bean
    public NewTopic adjustmentTopic() {
        return new NewTopic(topicName, 1, (short) 1); // Topic with 1 partition and replication factor of 1
    }
}
