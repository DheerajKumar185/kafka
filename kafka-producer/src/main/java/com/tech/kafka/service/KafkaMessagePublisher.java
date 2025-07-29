package com.tech.kafka.service;

import com.tech.kafka.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaMessagePublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaMessagePublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private <T> void sendMessage(String topic, T payload) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, payload);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Message sent to topic [{}]: {} with offset {}", topic, payload, result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send message to topic [{}]: {}", topic, ex.getMessage(), ex);
            }
        });
    }

    public void sendMessageToTopic(String message) {
        sendMessage("kafkaTopic1", message);
    }

    public void sendEventsToTopic(Customer customer) {
        sendMessage("kafkaTopic2", customer);
    }
}
