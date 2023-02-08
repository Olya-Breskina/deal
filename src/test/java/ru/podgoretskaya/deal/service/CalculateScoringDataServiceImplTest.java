package ru.podgoretskaya.deal.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.podgoretskaya.deal.client.ConveyorClient;
import ru.podgoretskaya.deal.mapper.ApplicationMapper;
import ru.podgoretskaya.deal.mapper.ClientMapper;
import ru.podgoretskaya.deal.repository.ApplicationRepo;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class CalculateScoringDataServiceImplTest {
    ObjectMapper objectMapper = new ObjectMapper();
    ApplicationRepo applicationRepo = Mockito.mock(ApplicationRepo.class);
    ConveyorClient conveyorClient = Mockito.mock(ConveyorClient.class);
    ApplicationMapper applicationMapper = Mockito.mock(ApplicationMapper.class);
    ClientMapper clientMapper = Mockito.mock(ClientMapper.class);
    CalculateScoringDataService calculateScoringDataService = new CalculateScoringDataServiceImpl(applicationRepo, conveyorClient, applicationMapper, clientMapper);

    @Test
    void calculateConditions() throws Exception {
        Mockito.when(applicationRepo.findById(anyLong())).thenReturn(Optional.empty());
        Long applicationId = Long.valueOf(5);
    }
}