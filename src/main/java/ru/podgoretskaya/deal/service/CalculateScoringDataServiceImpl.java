package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.CreditDTO;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.ScoringDataDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.ClientEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.exception.EntityeNotFoundException;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.repository.ClientRepo;
import ru.podgoretskaya.deal.repository.CreditRepo;


@Service
@RequiredArgsConstructor
@Slf4j
public class CalculateScoringDataServiceImpl implements CalculateScoringDataService  {
    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;


    @Override
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public void calculateConditions(FinishRegistrationRequestDTO model, Long applicationId)  {
        log.info("метод calculateConditions. Параметры: \"" + model.toString()+", "+applicationId);
        ApplicationEntity applicationEntity = applicationRepo.findById(applicationId).orElseThrow(() -> new EntityeNotFoundException(applicationId));
        ScoringDataDTO scoringDataDTO = buildScoringData(model, applicationEntity.getClient(),applicationEntity.getCredit(), applicationEntity);
        getCalculationPages(scoringDataDTO);
    }

    private ScoringDataDTO buildScoringData(FinishRegistrationRequestDTO model, ClientEntity clientEntity, CreditEntity creditEntity, ApplicationEntity applicationEntity) {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setFirstName(clientEntity.getFirstName());//  из бд
        scoringDataDTO.setLastName(clientEntity.getLastName());
        scoringDataDTO.setMiddleName(clientEntity.getMiddleName());
        scoringDataDTO.setBirthdate(clientEntity.getBirthdate());
        scoringDataDTO.setPassportNumber(clientEntity.getPassport().getNumber());
        scoringDataDTO.setPassportSeries(clientEntity.getPassport().getSeries());
        scoringDataDTO.setTerm(creditEntity.getTerm());
        scoringDataDTO.setAmount(creditEntity.getAmount());


        scoringDataDTO.setAccount(model.getAccount());//  из finish
        scoringDataDTO.setEmployment(model.getEmployment());
        scoringDataDTO.setPassportIssueBranch(model.getPassportIssueBranch());
        scoringDataDTO.setPassportIssueDate(model.getPassportIssueDate());
        scoringDataDTO.setMaritalStatus(model.getMaritalStatus());
        scoringDataDTO.setGender(model.getGender());

        scoringDataDTO.setIsInsuranceEnabled(applicationEntity.getAppliedOffer().getIsInsuranceEnabled());
        scoringDataDTO.setIsSalaryClient(applicationEntity.getAppliedOffer().getIsSalaryClient());
        log.info("заполнение scoringDataDTO. Параметры: \"" + scoringDataDTO);
        return scoringDataDTO;
    }

    private ResponseEntity<CreditDTO> getCalculationPages(ScoringDataDTO model) {
        return conveyorClient.getCalculationPages(model);
    }
}
