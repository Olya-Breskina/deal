package ru.podgoretskaya.deal.service;

import ru.podgoretskaya.deal.dto.ApplicationDTO;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;

import java.util.List;

public interface ApplicationService {
    List<LoanOfferDTO> calculateConditions(LoanApplicationRequestDTO model);
    ApplicationDTO findApplicationById (Long applicationId);
}
