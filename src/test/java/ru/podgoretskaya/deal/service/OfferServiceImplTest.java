package ru.podgoretskaya.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.exception.EntityeNotFoundException;
import ru.podgoretskaya.deal.json.StatusHistory;
import ru.podgoretskaya.deal.repository.ApplicationRepo;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static ru.podgoretskaya.deal.entityEnum.ApplicationStatus.PREAPPROVAL;
import static ru.podgoretskaya.deal.entityEnum.ChangeType.AUTOMATIC;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {
    ObjectMapper objectMapper = new ObjectMapper();
    ApplicationRepo applicationRepo= Mockito.mock(ApplicationRepo.class);
    ConveyorClient conveyorClient=Mockito.mock(ConveyorClient.class);
    OfferService offerService=new OfferServiceImpl(applicationRepo,conveyorClient);
    @Test
    void calculateConditions() throws Exception{
        Mockito.when(applicationRepo.findById(anyLong())).thenReturn(Optional.empty());
        LoanOfferDTO loanOfferDTO = objectMapper.readValue(new File("src/test/resources/applicationService/LoanOfferDTO.json"), LoanOfferDTO.class);

        EntityeNotFoundException thrown = Assertions.assertThrows(EntityeNotFoundException.class, () -> {
            offerService.calculateConditions(loanOfferDTO);});
        Assertions.assertEquals("Entity with id = " + loanOfferDTO.getApplicationId() + " not found", thrown.getMessage());


    }
}