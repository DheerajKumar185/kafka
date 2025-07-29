package com.tech.kafka.consumer;

import com.tech.kafka.dto.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    /*@KafkaListener(topics = "kafkaTopic3", groupId = "consumer-group3")
    public void consume1(String message) {
        logger.info("Consumer1 consume the message: {}", message);
    }

    @KafkaListener(topics = "kafkaTopic3", groupId = "consumer-group3")
    public void consume2(String message) {
        logger.info("Consumer2 consume the message: {}", message);
    }

    @KafkaListener(topics = "kafkaTopic3", groupId = "consumer-group3")
    public void consume3(String message) {
        logger.info("Consumer3 consume the message: {}", message);
    }

    @KafkaListener(topics = "kafkaTopic3", groupId = "consumer-group3")
    public void consume4(String message) {
        logger.info("Consumer4 consume the message: {}", message);
    }*/

    @KafkaListener(topics = "kafkaTopic2", groupId = "consumer-group2")
    public void consumeEvents(Customer customer) {
        logger.info("consumer consume the events: {}", customer);
    }
}
