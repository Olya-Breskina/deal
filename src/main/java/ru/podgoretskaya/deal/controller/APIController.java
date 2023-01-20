package ru.podgoretskaya.deal.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.dto.ScoringDataDTO;
import ru.podgoretskaya.deal.service.ApplicationService;
import ru.podgoretskaya.deal.service.OfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deal")
@Slf4j//логгер

public class APIController {
    private final ApplicationService applicationService;//метод 1
    private final OfferService offerService;// метод 2
    LoanApplicationRequestDTO operationOffersModel = new LoanApplicationRequestDTO();
    LoanOfferDTO loanOfferDTO=new LoanOfferDTO();
    FinishRegistrationRequestDTO finishRegistrationRequestDTO=new FinishRegistrationRequestDTO();
    ScoringDataDTO operationCalculationModel = new ScoringDataDTO();
    /* todo
    private final  ApplicationIdService  applicationIdService;// метод 3
*/

    @PostMapping(value = "/application")
    public ResponseEntity<List<LoanOfferDTO>> getOffersPages(@RequestBody LoanApplicationRequestDTO model) {
        log.info("вызов /application. Параметры: \"" + model.toString());
        try {
            return new ResponseEntity<>(applicationService.calculateConditions(model), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

   /* @PutMapping(value = "/offer")
    public void updateStatusHistory(@RequestBody LoanOfferDTO model) {
        offerService.calculateConditions(model);
    }
    @PutMapping(value = "/calculate/{applicationId}")
    public void updateStatus(@RequestBody FinishRegistrationRequestDTO model,@PathVariable Long applicationId)
    {
    }*/

}