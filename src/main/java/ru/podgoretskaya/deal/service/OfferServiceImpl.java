package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.exception.EntityeNotFoundException;
import ru.podgoretskaya.deal.json.StatusHistory;
import ru.podgoretskaya.deal.mapper.CreditMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.repository.CreditRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.podgoretskaya.deal.entityEnum.ApplicationStatus.PREAPPROVAL;
import static ru.podgoretskaya.deal.entityEnum.ChangeType.AUTOMATIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;

    @Override
    public void calculateConditions(LoanOfferDTO model) {
        log.info("метод calculateConditions. Параметры: \"" + model.toString());
        ApplicationEntity applicationEntity = applicationRepo.findById(model.getApplicationId()).orElseThrow(()->new EntityeNotFoundException(model.getApplicationId()));
        applicationEntity.setStatus(PREAPPROVAL);//ApplicationStatus

        List<StatusHistory> historyStatuses = new ArrayList<>();
        historyStatuses.add(new StatusHistory(PREAPPROVAL, LocalDateTime.now(),AUTOMATIC));
        applicationEntity.setStatusHistiry(historyStatuses);
      applicationEntity.setAppliedOffer(model);
        applicationRepo.save(applicationEntity);
    }

}
