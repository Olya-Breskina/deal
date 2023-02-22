package ru.podgoretskaya.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.EmailMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class DealKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    private void sendMessage(EmailMessage message, String topicName) {
        kafkaTemplate.send(topicName, converMessageToJson(message));
        log.info("отправлено сообщение:{}, в топик: {}", message, topicName);
    }

    private String converMessageToJson(EmailMessage message) {
        try {
            return new ObjectMapper().writeValueAsString(message);
        } catch (Exception e) {
            log.info("failed to parse message: {}", e);
            throw new IllegalArgumentException(e.getMessage());
        }
    }


    public void finishRegistration(EmailMessage message) {
        sendMessage(message, "finish-registration");
    }

    public void createDocuments(EmailMessage message) {
        sendMessage(message, "create-documents");
    }

    public void sendDocuments(EmailMessage message) {
        sendMessage(message, "send-documents");
    }

    public void sendSes(EmailMessage message) {
        sendMessage(message, "send-ses");
    }

    public void creditIssued(EmailMessage message) {
        sendMessage(message, "credit-issued");
    }

    public void applicationDenied(EmailMessage message) {
        sendMessage(message, "application-denied");
    }
}
