package ru.podgoretskaya.deal.mapper;

import org.springframework.stereotype.Component;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.CreditEntity;

@Component
public class CreditMapper {
    public CreditEntity loanApplicationRequestDTOMapToEntity(LoanApplicationRequestDTO model){
        CreditEntity creditEntity=new CreditEntity();
        creditEntity.setAmount(model.getAmount());
        creditEntity.setTerm(model.getTerm());
        return creditEntity;
    }
    public CreditEntity loanOfferDTOMapToEntity(LoanOfferDTO model){
        CreditEntity creditEntity=new CreditEntity();
        creditEntity.setClientID(model.getApplicationId());
        creditEntity.setAmount(model.getRequestedAmount());
        creditEntity.setAmount(model.getTotalAmount());
        creditEntity.setRate(model.getRate());
        creditEntity.setTerm(model.getTerm());
        creditEntity.setMonthPayment(model.getMonthlyPayment());
        creditEntity.setInsuranceEnabled(model.getIsInsuranceEnabled());
        creditEntity.setSalaryClient(model.getIsSalaryClient());
        return creditEntity;
    }
}
