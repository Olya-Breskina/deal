package ru.podgoretskaya.deal.service;

import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;

public interface CalculateScoringDataService {
    void calculateConditions(FinishRegistrationRequestDTO model,Long applicationId);

}
