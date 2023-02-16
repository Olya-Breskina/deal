package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.CreditDTO;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.ScoringDataDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.exception.EntityNotFoundException;
import ru.podgoretskaya.deal.mapper.ApplicationMapper;
import ru.podgoretskaya.deal.mapper.ClientMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.util.HistiryManagerUtil;

import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.CC_APPROVED;
import static ru.podgoretskaya.deal.entity_enum.ApplicationStatus.CC_DENIED;
import static ru.podgoretskaya.deal.entity_enum.CreditStatus.CALCULATED;


@Service
@RequiredArgsConstructor
@Slf4j
public class CalculateScoringDataServiceImpl implements CalculateScoringDataService {
    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;
    private final ApplicationMapper applicationMapper;
    private final ClientMapper clientMapper;

    @Override
    public void calculateConditions(FinishRegistrationRequestDTO model, Long applicationId) {
        log.info("метод calculateConditions. Параметры: \"" + model.toString() + ", " + applicationId);
        ApplicationEntity applicationEntity = applicationRepo.findById(applicationId).orElseThrow(() -> new EntityNotFoundException(applicationId));
        ScoringDataDTO scoringDataDTO = applicationMapper.scoringDataDTOMapToEntity(model, applicationEntity);
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

            HistiryManagerUtil.updateStatus(applicationEntity, CC_APPROVED);
        } catch (NullPointerException e) {
            HistiryManagerUtil.updateStatus(applicationEntity, CC_DENIED);
        } finally {
            applicationRepo.save(applicationEntity);
        }
    }

    private CreditDTO getCalculationPages(ScoringDataDTO model) {
        return conveyorClient.getCalculationPages(model);
    }
}
