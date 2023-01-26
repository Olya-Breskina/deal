package ru.podgoretskaya.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
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

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ApplicationServiceImplTest {
    ObjectMapper objectMapper = new ObjectMapper();

    ClientMapper clientMapper=new ClientMapper();
    CreditMapper creditMapper=new CreditMapper();
    ClientRepo clientRepo=Mockito.mock(ClientRepo.class);
    CreditRepo creditRepo=Mockito.mock(CreditRepo.class);
    ApplicationRepo applicationRepo=Mockito.mock(ApplicationRepo.class);
    ConveyorClient conveyorClient=Mockito.mock(ConveyorClient.class);
    ApplicationService applicationService= new ApplicationServiceImpl(clientMapper,clientRepo,creditMapper,creditRepo,applicationRepo,conveyorClient);

    @BeforeEach
    void beforeAll() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void calculateConditions() throws IOException {

        LoanOfferDTO loanOfferDTO = objectMapper.readValue(new File("src/test/resources/applicationService/LoanOfferDTO.json"), LoanOfferDTO.class);
        Mockito.when(conveyorClient.getOffersPages(Mockito.any())).thenReturn(Collections.singletonList(loanOfferDTO));
        LoanApplicationRequestDTO loanApplicationRequestDTO = objectMapper.readValue(new File("src/test/resources/applicationService/test.json"),
                LoanApplicationRequestDTO.class);
        ClientEntity clientEntity = clientMapper.loanApplicationRequestDTOMapToEntity(loanApplicationRequestDTO);
        CreditEntity creditEntity = creditMapper.loanApplicationRequestDTOMapToEntity(loanApplicationRequestDTO);
        ApplicationEntity applicationEntity=new ApplicationEntity();
        applicationEntity.setCredit(creditEntity);
        applicationEntity.setClient(clientEntity);

        List<LoanOfferDTO> loanOfferDTOS = applicationService.calculateConditions(loanApplicationRequestDTO);

        Mockito.verify(clientRepo).save(clientEntity);
        Mockito.verify(creditRepo).save(creditEntity);
        Mockito.verify(applicationRepo).save(applicationEntity);

        Mockito.verify(conveyorClient).getOffersPages(loanApplicationRequestDTO);


    }
}