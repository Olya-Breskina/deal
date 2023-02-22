package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.EmailMessage;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.util.HistiryManagerUtil;

import static ru.podgoretskaya.deal.dto.Theme.FINISH_REGISTRATION;
import static ru.podgoretskaya.deal.dto.Theme.SEND_DOCUMENTS;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.DOCUMENT_CREATED;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.PREAPPROVAL;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendService {
    private final ApplicationRepo applicationRepo;
    private final DealKafkaProducer dealKafkaProducer;


    public void sendDocuments(Long applicationId){
        ApplicationEntity applicationEntity= applicationRepo.findById(applicationId).orElseThrow(IllegalArgumentException::new);
            applicationEntity.setStatus(DOCUMENT_CREATED);//ApplicationStatus
            HistiryManagerUtil.updateStatus(applicationEntity, applicationEntity.getStatus());
            applicationRepo.save(applicationEntity);

            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setAddress(applicationEntity.getClient().getEmail());
            emailMessage.setTheme(SEND_DOCUMENTS);
            emailMessage.setApplicationId(applicationEntity.getApplicationID());
            dealKafkaProducer.sendDocuments(emailMessage);
        }
    }

