package com.tech.kafka.consumer;

import com.tech.kafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    /*@KafkaListener(topics = "kafkaTopic1", groupId = "consumer-group1")
    public void consumeEvents(String message) {
        logger.info("consumer consume the events: {}", message);
    }*/

    @KafkaListener(topics = "kafkaTopic2", groupId = "consumer-group2")
    public void consumeEvents(Customer customer) {
        logger.info("consumer consume the events: {}", customer);
    }
}
