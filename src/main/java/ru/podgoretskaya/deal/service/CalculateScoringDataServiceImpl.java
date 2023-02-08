package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.*;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.exception.EntityNotFoundException;
import ru.podgoretskaya.deal.mapper.ApplicationMapper;
import ru.podgoretskaya.deal.mapper.ClientMapper;
import ru.podgoretskaya.deal.mapper.CreditMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.util.HistiryManagerUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.podgoretskaya.deal.dto.Theme.CREATE_DOCUMENTS;
import static ru.podgoretskaya.deal.dto.Theme.FINISH_REGISTRATION;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.CC_APPROVED;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.CC_DENIED;
import static ru.podgoretskaya.deal.entity_enum.ChangeType.AUTOMATIC;
import static ru.podgoretskaya.deal.entity_enum.CreditStatus.CALCULATED;


@Service
@RequiredArgsConstructor
@Slf4j
public class CalculateScoringDataServiceImpl implements CalculateScoringDataService {
    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;
    private final ApplicationMapper applicationMapper;
    private final ClientMapper clientMapper;
    private final DealKafkaProducer dealKafkaProducer;


    @Override
    public void calculateConditions(FinishRegistrationRequestDTO model, Long applicationId) {
        log.info("метод calculateConditions. Параметры: \"" + model.toString() + ", " + applicationId);
        ApplicationEntity applicationEntity = applicationRepo.findById(applicationId).orElseThrow(() -> new EntityNotFoundException(applicationId));
        ScoringDataDTO scoringDataDTO = buildScoringData(model, applicationEntity);
        applicationEntity = clientMapper.finishRegistrationRequestDTOMapToEntity(model, applicationEntity);
        try {
            CreditDTO creditALL = getCalculationPages(scoringDataDTO);
            CreditEntity creditEntity = applicationEntity.getCredit();
            creditEntity.setAmount(creditALL.getAmount());
            creditEntity.setTerm(creditALL.getTerm());
            creditEntity.setRate(creditALL.getRate());
            creditEntity.setPsk(creditALL.getPsk());
            creditEntity.setMonthPayment(creditALL.getMonthlyPayment());
            creditEntity.setInsuranceEnabled(creditALL.getIsInsuranceEnabled());
            creditEntity.setSalaryClient(creditALL.getIsSalaryClient());
            creditEntity.setCreditStatus(CALCULATED);
            creditEntity.setPaymentSchedule(creditALL.getPaymentSchedule());

            applicationEntity.setCredit(creditEntity);

            applicationEntity.setStatus(CC_APPROVED);
            HistiryManagerUtil.updateStatus(applicationEntity, applicationEntity.getStatus());
        } catch (NullPointerException e) {
            applicationEntity.setStatus(CC_DENIED);
            HistiryManagerUtil.updateStatus(applicationEntity, applicationEntity.getStatus());
        } finally {
            applicationRepo.save(applicationEntity);
        }
    }

    private ScoringDataDTO buildScoringData(FinishRegistrationRequestDTO model, ApplicationEntity applicationEntity) {
        ScoringDataDTO scoringDataDTO = applicationMapper.scoringDataDTOMapToEntity(model, applicationEntity);
        applicationEntity.setStatus(CC_APPROVED);
        List<StatusHistory> historyStatuses = new ArrayList<>();
        historyStatuses.add(new StatusHistory(CC_APPROVED, LocalDateTime.now(), AUTOMATIC));
        applicationEntity.setStatusHistory(historyStatuses);
        applicationRepo.save(applicationEntity);
        return scoringDataDTO;
    }

    private CreditDTO getCalculationPages(ScoringDataDTO model) {
        return conveyorClient.getCalculationPages(model);
    }
    private void createDocuments(ApplicationEntity applicationEntity){
        EmailMessage emailMessage=new EmailMessage();
        emailMessage.setAddress(applicationEntity.getClient().getEmail());
        emailMessage.setTheme(CREATE_DOCUMENTS);
        emailMessage.setApplicationId(applicationEntity.getApplicationID());
        dealKafkaProducer.createDocuments(emailMessage);
    }
}
