package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.dto.StatusHistory;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.exception.EntityNotFoundException;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.util.HistiryManagerUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.APPROVED;
import static ru.podgoretskaya.deal.entity_enum.ChangeType.AUTOMATIC;

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

        HistiryManagerUtil.updateStatus(applicationEntity,applicationEntity.getStatus());
        applicationEntity.setAppliedOffer(model);
        applicationRepo.save(applicationEntity);
    }

}
