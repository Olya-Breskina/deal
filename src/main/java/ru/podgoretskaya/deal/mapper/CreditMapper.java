package ru.podgoretskaya.deal.mapper;

import org.springframework.stereotype.Component;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.entity.CreditEntity;

@Component
public class CreditMapper {
    public CreditEntity loanApplicationRequestDTOMapToEntity(LoanApplicationRequestDTO model){
        CreditEntity creditEntity=new CreditEntity();
        creditEntity.setAmount(model.getAmount());
        creditEntity.setTerm(model.getTerm());
        return creditEntity;
    }
}
