/*
package com.example.consumerservice.jobs;

import com.example.consumerservice.kafka.KafkaConsumerService;
import com.example.consumerservice.model.AdjustmentSSY;
import com.example.consumerservice.model.AdjustmentSSYKafka;
import com.example.consumerservice.repository.AdjustmentSSYRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MessageProcessingJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(MessageProcessingJob.class);

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @Autowired
    private AdjustmentSSYRepository adjustmentSSYRepository;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Processing messages from Kafka...");

        // Fetch messages from Kafka
        List<AdjustmentSSYKafka> messages = kafkaConsumerService.fetchMessagesFromKafka();


        // Save messages to the database
        for (AdjustmentSSYKafka message : messages) {
            AdjustmentSSY adjustmentSSY = AdjustmentSSY.fromKafkaModel(message);
            adjustmentSSYRepository.save(adjustmentSSY);
            logger.info("Saved adjustment to database: {}", message);
        }

        logger.info("Finished processing {} messages.", messages.size());
    }
}
*/
