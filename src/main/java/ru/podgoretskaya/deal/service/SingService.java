package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.EmailMessage;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.repository.ApplicationRepo;

import java.util.Random;

import static ru.podgoretskaya.deal.dto.Theme.SEND_SES;

@Service
@RequiredArgsConstructor
@Slf4j
public class SingService {
    private final ApplicationRepo applicationRepo;
    private final DealKafkaProducer dealKafkaProducer;

    public void sendSes(ApplicationEntity applicationEntity) {

        int[] sesCodeArray = new int[4];
        for (int i = 0; i < 4; i++) {
            Random random = new Random();
            int x = random.nextInt(9);
            sesCodeArray[i] = x;
        }
        String sesCode = "" + sesCodeArray[0] + sesCodeArray[1] + sesCodeArray[2] + sesCodeArray[3];
        applicationEntity.setSesCode(sesCode);

        //куда деть код?

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setAddress(applicationEntity.getClient().getEmail());
        emailMessage.setTheme(SEND_SES);
        emailMessage.setApplicationId(applicationEntity.getApplicationID());
        dealKafkaProducer.sendDocuments(emailMessage);
    }
}


