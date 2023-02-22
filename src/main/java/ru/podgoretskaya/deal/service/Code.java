package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.EmailMessage;
import ru.podgoretskaya.deal.dto.Theme;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.util.HistiryManagerUtil;

import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.CREDIT_ISSUED;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.DOCUMENT_SIGNED;

@Service
@RequiredArgsConstructor
@Slf4j
public class Code {
    private final ApplicationRepo applicationRepo;
    private final DealKafkaProducer dealKafkaProducer;


    public void verifyingSesCode(Long applicationId, String sesCode) {
        ApplicationEntity applicationEntity = applicationRepo.findById(applicationId).orElseThrow(IllegalArgumentException::new);
        if (applicationEntity.getSesCode().equals(sesCode)) {
            sesCodeTRUE(applicationEntity);
            applicationEntity.setStatus(CREDIT_ISSUED);//ApplicationStatus
            HistiryManagerUtil.updateStatus(applicationEntity, applicationEntity.getStatus());
            applicationRepo.save(applicationEntity);
        }
    }

    private void sesCodeTRUE(ApplicationEntity applicationEntity) {
        applicationEntity.setStatus(DOCUMENT_SIGNED);//ApplicationStatus
        HistiryManagerUtil.updateStatus(applicationEntity, applicationEntity.getStatus());
        applicationRepo.save(applicationEntity);

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setAddress(applicationEntity.getClient().getEmail());
        emailMessage.setTheme(Theme.CREDIT_ISSUED);
        emailMessage.setApplicationId(applicationEntity.getApplicationID());
        dealKafkaProducer.sendDocuments(emailMessage);
    }

}

