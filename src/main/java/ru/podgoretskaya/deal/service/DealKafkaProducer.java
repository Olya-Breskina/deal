package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private void sendMessage(String message, String topicName) {
        kafkaTemplate.send(topicName, message);
        log.info("отправлено сообщение:{}, в топик: {}", message, topicName);
    }

    public void finishRegistration(String message) {
        sendMessage(message, "finish-registration");
    }

    public void createDocuments(String message) {
        sendMessage(message, "create-documents");
    }

    public void sendDocuments(String message) {
        sendMessage(message, "send-documents");
    }

    public void sendSes(String message) {
        sendMessage(message, "send-ses");
    }

    public void creditIssued(String message) {
        sendMessage(message, "credit-issued");
    }

    public void applicationDenied(String message) {
        sendMessage(message, "application-denied");
    }
}
