package ru.podgoretskaya.deal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.ClientEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.mapper.ClientMapper;
import ru.podgoretskaya.deal.mapper.CreditMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;
import ru.podgoretskaya.deal.repository.ClientRepo;
import ru.podgoretskaya.deal.repository.CreditRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ClientMapper clientMapper;
    private final ClientRepo clientRepo;
    private final CreditMapper creditMapper;
    private final CreditRepo creditRepo;
    private final ApplicationRepo applicationRepo;
    private final ConveyorClient conveyorClient;

    @Override
    public List<LoanOfferDTO> calculateConditions(LoanApplicationRequestDTO model) {
        ClientEntity clientEntity = saveClientToDB(model);
        CreditEntity creditEntity = saveCreditToDB(model);
        saveApplicationToDB(clientEntity, creditEntity);
      // saveApplicationToDB(model);
        return sentRequestToConveyorService(model);
    }

    private ClientEntity saveClientToDB(LoanApplicationRequestDTO model) {
        ClientEntity clientEntity = clientMapper.mapToEntity(model);
        return clientRepo.save(clientEntity);
    }

    private CreditEntity saveCreditToDB(LoanApplicationRequestDTO model) {
        CreditEntity creditEntity = creditMapper.loanApplicationRequestDTOMapToEntity(model);
        return creditRepo.save(creditEntity);
    }

        private ApplicationEntity saveApplicationToDB(ClientEntity clientEntity, CreditEntity creditEntity) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setClient(clientEntity);
        applicationEntity.setCredit(creditEntity);
        return applicationRepo.save(applicationEntity);
    }
   /*
    private ApplicationEntity saveApplicationToDB(LoanApplicationRequestDTO model) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        ClientEntity clientEntity = clientMapper.mapToEntity(model);
        CreditEntity creditEntity = creditMapper.loanApplicationRequestDTOMapToEntity(model);
        applicationEntity.setClient(clientEntity);
        applicationEntity.setCredit(creditEntity);
        //todo статус, дата, статус истории

        return applicationRepo.save(applicationEntity);
    }*/

    private List<LoanOfferDTO> sentRequestToConveyorService(LoanApplicationRequestDTO model) {
        return conveyorClient.getOffersPages(model);
    }
}
