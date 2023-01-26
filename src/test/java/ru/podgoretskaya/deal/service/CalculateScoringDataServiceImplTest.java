package ru.podgoretskaya.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.exception.EntityeNotFoundException;
import ru.podgoretskaya.deal.repository.ApplicationRepo;

import java.io.File;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class CalculateScoringDataServiceImplTest {
    ObjectMapper objectMapper = new ObjectMapper();
    ApplicationRepo applicationRepo= Mockito.mock(ApplicationRepo.class);
    ConveyorClient conveyorClient=Mockito.mock(ConveyorClient.class);
    CalculateScoringDataService calculateScoringDataService=new CalculateScoringDataServiceImpl(applicationRepo,conveyorClient);
    @Test
    void calculateConditions() throws Exception{
        Mockito.when(applicationRepo.findById(anyLong())).thenReturn(Optional.empty());
        Long applicationId=Long.valueOf(5);
    }
}