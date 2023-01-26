package ru.podgoretskaya.deal.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.entity.ClientEntity;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
class ClientMapperTest {
    ObjectMapper objectMapper = new ObjectMapper();
    ClientMapper clientMapper=new ClientMapper();
    @BeforeEach
    void beforeAll() {
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Test
    void loanApplicationRequestDTOMapToEntity() throws Exception {
        LoanApplicationRequestDTO loanApplicationRequestDTO = objectMapper.readValue(new File("src/test/resources/applicationService/test.json"),
                LoanApplicationRequestDTO.class);
        ClientEntity clientEntity = clientMapper.loanApplicationRequestDTOMapToEntity(loanApplicationRequestDTO);
        assertNotNull(clientEntity);
        assertEquals( clientEntity.getFirstName(),loanApplicationRequestDTO.getFirstName());
        assertEquals( clientEntity.getLastName(),loanApplicationRequestDTO.getLastName());
        assertEquals( clientEntity.getMiddleName(),loanApplicationRequestDTO.getMiddleName());
        assertEquals( clientEntity.getBirthdate(),loanApplicationRequestDTO.getBirthdate());
        assertEquals( clientEntity.getEmail(),loanApplicationRequestDTO.getEmail());
        //паспорт
    }
}