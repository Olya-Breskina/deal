package ru.podgoretskaya.deal.mapper;

import org.springframework.stereotype.Component;
import ru.podgoretskaya.deal.dto.ApplicationDTO;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.ScoringDataDTO;
import ru.podgoretskaya.deal.dto.StatusHistory;
import ru.podgoretskaya.deal.entity.ApplicationEntity;

@Component
public class ApplicationMapper {
    public static ApplicationDTO mapEntityToDTO(ApplicationEntity applicationEntity) {
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setApplicationID(applicationEntity.getApplicationID());
        applicationDTO.setStatus(applicationEntity.getStatus());
        applicationDTO.setSesCode(applicationEntity.getSesCode());
        applicationDTO.setCreationDate(applicationEntity.getCreationDate());
        applicationDTO.setStatusHistory(applicationEntity.getStatusHistory());
        applicationDTO.setAppliedOffer(applicationEntity.getAppliedOffer());
        return applicationDTO;
    }

    public ScoringDataDTO scoringDataDTOMapToEntity(FinishRegistrationRequestDTO model, ApplicationEntity applicationEntity) {
        ScoringDataDTO scoringDataDTO = new ScoringDataDTO();
        scoringDataDTO.setFirstName(applicationEntity.getClient().getFirstName());//  из бд
        scoringDataDTO.setLastName(applicationEntity.getClient().getLastName());
        scoringDataDTO.setMiddleName(applicationEntity.getClient().getMiddleName());
        scoringDataDTO.setBirthdate(applicationEntity.getClient().getBirthdate());
        scoringDataDTO.setPassportNumber(applicationEntity.getClient().getPassport().getNumber());
        scoringDataDTO.setPassportSeries(applicationEntity.getClient().getPassport().getSeries());

        scoringDataDTO.setTerm(applicationEntity.getCredit().getTerm());
        scoringDataDTO.setAmount(applicationEntity.getCredit().getAmount());


        scoringDataDTO.setAccount(model.getAccount());//  из finish
        scoringDataDTO.setEmployment(model.getEmployment());
        scoringDataDTO.setPassportIssueBranch(model.getPassportIssueBranch());
        scoringDataDTO.setPassportIssueDate(model.getPassportIssueDate());
        scoringDataDTO.setMaritalStatus(model.getMaritalStatus());
        scoringDataDTO.setGender(model.getGender());

        scoringDataDTO.setIsInsuranceEnabled(applicationEntity.getAppliedOffer().getIsInsuranceEnabled());
        scoringDataDTO.setIsSalaryClient(applicationEntity.getAppliedOffer().getIsSalaryClient());
        return scoringDataDTO;
    }
}
