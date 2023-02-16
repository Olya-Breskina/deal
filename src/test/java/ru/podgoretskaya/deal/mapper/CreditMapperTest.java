package ru.podgoretskaya.deal.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.entity.CreditEntity;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class CreditMapperTest {
    ObjectMapper objectMapper = new ObjectMapper();
    CreditMapper creditMapper=new CreditMapper();
    @BeforeEach
    void beforeAll() {
        objectMapper.registerModule(new JavaTimeModule());
    }
    @Test
    void loanApplicationRequestDTOMapToEntity() throws Exception{
        LoanApplicationRequestDTO loanApplicationRequestDTO = objectMapper.readValue(new File("src/test/resources/applicationService/test.json"),
                LoanApplicationRequestDTO.class);
        CreditEntity creditEntity = creditMapper.loanApplicationRequestDTOMapToEntity(loanApplicationRequestDTO);
        assertNotNull(creditEntity);
        assertEquals(creditEntity.getAmount(),loanApplicationRequestDTO.getAmount());
        assertEquals(creditEntity.getTerm(),loanApplicationRequestDTO.getTerm());
    }
}