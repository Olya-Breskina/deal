package ru.podgoretskaya.deal.mapper;

import org.springframework.stereotype.Component;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.Passport;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.ClientEntity;

@Component
public class ClientMapper {
    public ClientEntity loanApplicationRequestDTOMapToEntity(LoanApplicationRequestDTO model) {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setFirstName(model.getFirstName());
        clientEntity.setLastName(model.getLastName());
        clientEntity.setMiddleName(model.getMiddleName());
        clientEntity.setBirthdate(model.getBirthdate());
        clientEntity.setEmail(model.getEmail());
        //для заполнения паспорта
        Passport passport = new Passport();
        passport.setNumber(model.getPassportNumber());
        passport.setSeries(model.getPassportSeries());
        clientEntity.setPassport(passport);
        //
        return clientEntity;
    }

    public ApplicationEntity finishRegistrationRequestDTOMapToEntity(FinishRegistrationRequestDTO model, ApplicationEntity applicationEntity) {
        applicationEntity.getClient().setAccount(model.getAccount());
        applicationEntity.getClient().setGender(model.getGender());
        applicationEntity.getClient().setMaritalStatus(model.getMaritalStatus());
        applicationEntity.getClient().setEmployment(model.getEmployment());

        return applicationEntity;
    }
}
