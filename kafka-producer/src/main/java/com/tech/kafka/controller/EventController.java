package com.tech.kafka.controller;

import com.tech.kafka.dto.Customer;
import com.tech.kafka.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EventController {
    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable("message") String message) {
        try {
            for (int i = 0; i < 100000; i++) {
                kafkaMessagePublisher.sendMessageToTopic(message + i);
            }
            return ResponseEntity.ok("message publish Successfully...");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publishEvents(@RequestBody Customer customer) {
        try {
            for (int i = 0; i < 100000; i++) {
                String modifiedName = customer.getName().toLowerCase() + i;
                String modifiedEmail = customer.getEmail().replace(customer.getName().toLowerCase(), modifiedName);
                Customer customer1 = new Customer(i, modifiedName, modifiedEmail, customer.getContactNo());
                kafkaMessagePublisher.sendEventsToTopic(customer1);
            }
            return ResponseEntity.ok("events publish Successfully...");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
