package ru.podgoretskaya.deal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.podgoretskaya.deal.dto.CreditDTO;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.dto.ScoringDataDTO;

import java.util.List;

@FeignClient(name = "conveyor",url ="http://localhost:8080")
public interface ConveyorClient {
    @PostMapping(value = "/conveyor/offers")
   List<LoanOfferDTO>  getOffersPages(@RequestBody LoanApplicationRequestDTO model);

    @PostMapping(value = "/conveyor/calculation")
    ResponseEntity<CreditDTO> getCalculationPages(@RequestBody ScoringDataDTO model);//название отличается от названия в MVP1 (в MVP1 ошибка в названии)
}
