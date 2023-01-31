package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.exception.EntityNotFoundException;
import ru.podgoretskaya.deal.json.StatusHistory;
import ru.podgoretskaya.deal.repository.ApplicationRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.podgoretskaya.deal.entityEnum.ApplicationStatus.APPROVED;
import static ru.podgoretskaya.deal.entityEnum.ChangeType.AUTOMATIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final ApplicationRepo applicationRepo;

    @Override
    public void calculateConditions(LoanOfferDTO model) {
        log.info("метод calculateConditions. Параметры: \"" + model.toString());
        ApplicationEntity applicationEntity = applicationRepo.findById(model.getApplicationId()).orElseThrow(() -> new EntityNotFoundException(model.getApplicationId()));
        applicationEntity.setStatus(APPROVED);//ApplicationStatus

        List<StatusHistory> historyStatuses = new ArrayList<>();
        historyStatuses.add(new StatusHistory(APPROVED, LocalDateTime.now(), AUTOMATIC));
        applicationEntity.setStatusHistiry(historyStatuses);
        applicationEntity.setAppliedOffer(model);
        applicationRepo.save(applicationEntity);
    }

}
