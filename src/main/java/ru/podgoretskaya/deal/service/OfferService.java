package ru.podgoretskaya.deal.service;

import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;

import java.util.List;

public interface OfferService {
    void calculateConditions(LoanOfferDTO model);
}
