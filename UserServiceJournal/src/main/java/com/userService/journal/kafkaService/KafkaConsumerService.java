package com.userService.journal.kafkaService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.userService.journal.service.JournalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private JournalService journalService;

    @KafkaListener(topics = "user-events", groupId = "user-events-group")
    public void consume(ConsumerRecord<String, String> record) {
        String message = record.value();
        logger.info("Received Kafka message: {}", message);

        // Store the message in the database
        journalService.recordEvent(message);
        
        
    }
}

