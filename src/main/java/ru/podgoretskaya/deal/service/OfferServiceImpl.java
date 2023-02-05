package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.EmailMessage;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.dto.StatusHistory;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.exception.EntityNotFoundException;
import ru.podgoretskaya.deal.repository.ApplicationRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.podgoretskaya.deal.dto.Theme.FINISH_REGISTRATION;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.APPROVED;
import static ru.podgoretskaya.deal.entity_enum.ChangeType.AUTOMATIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final ApplicationRepo applicationRepo;
    private final DealKafkaProducer dealKafkaProducer;

    @Override
    public void calculateConditions(LoanOfferDTO model) {
        log.info("метод calculateConditions. Параметры: \"" + model.toString());
        ApplicationEntity applicationEntity = applicationRepo.findById(model.getApplicationId()).orElseThrow(() -> new EntityNotFoundException(model.getApplicationId()));
        applicationEntity.setStatus(APPROVED);//ApplicationStatus

        List<StatusHistory> historyStatuses = new ArrayList<>();
        historyStatuses.add(new StatusHistory(APPROVED, LocalDateTime.now(), AUTOMATIC));
        applicationEntity.setStatusHistory(historyStatuses);
        applicationEntity.setAppliedOffer(model);
        applicationRepo.save(applicationEntity);
        finishRegistratoin(applicationEntity);
    }
    private void finishRegistratoin(ApplicationEntity applicationEntity){
        EmailMessage emailMessage=new EmailMessage();
        emailMessage.setAddress(applicationEntity.getClient().getEmail());
        emailMessage.setTheme(FINISH_REGISTRATION);
        emailMessage.setApplicationId(applicationEntity.getApplicationID());
        dealKafkaProducer.finishRegistration(emailMessage.toString());
    }

}
