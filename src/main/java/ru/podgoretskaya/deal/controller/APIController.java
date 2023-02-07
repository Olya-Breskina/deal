package ru.podgoretskaya.deal.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.podgoretskaya.deal.dto.FinishRegistrationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanApplicationRequestDTO;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.service.ApplicationService;
import ru.podgoretskaya.deal.service.CalculateScoringDataService;
import ru.podgoretskaya.deal.service.Code;
import ru.podgoretskaya.deal.service.OfferService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping("/deal")
@Slf4j//логгер
@Tag(name = "Реализация микросервиса Сделка (deal)", description = "Определение API")
public class APIController {
    private final ApplicationService applicationService;//метод 1
    private final OfferService offerService;// метод 2
    private final CalculateScoringDataService calculateScoringDataService;// метод 3
    private final Code code;// ses_code


    @PostMapping(value = "/application")
    @Operation(summary = "запрос данных прескоринга у conveyor")
    public ResponseEntity<List<LoanOfferDTO>> getOffersPages(@RequestBody LoanApplicationRequestDTO model) {
        log.info("вызов /application. Параметры: \"" + model.toString());
        try {
            return new ResponseEntity<>(applicationService.calculateConditions(model), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/offer")
    @Operation(summary = "сохранение выбранного варианта прескоринга")
    public void updateStatusHistory(@RequestBody LoanOfferDTO model) {
        log.info("вызов /offer. Параметры: \"" + model.toString());
        offerService.calculateConditions(model);
    }

    @PutMapping(value = "/calculate/{applicationId}")
    @Operation(summary = "запрос данных скоринга у conveyor")
    public void updateStatus(@RequestBody FinishRegistrationRequestDTO model, @PathVariable Long applicationId) {
        log.info("вызов /calculate/{applicationId}. Параметры: \"" + model.toString() + ", " + applicationId);
        calculateScoringDataService.calculateConditions(model, applicationId);
        //если правильно понимаю, тут должна быть кафка и отправка сообщения
    }
    @PostMapping(value = "/document/{applicationId}/send")
    @Operation(summary = "запрос на отправку документов")
    public void send (@PathVariable Long applicationId) {
        //бд, кафка
    }
//    @PostMapping(value = "/document/{applicationId}/sign")
//    @Operation(summary = "запрос на подписание документов")
//    public ResponseEntity<List<LoanOfferDTO>> getOffersPages7(@RequestBody LoanApplicationRequestDTO model) {
//    }
    @PostMapping(value = "/document/{applicationId}/code")
    @Operation(summary = "подписание документов")
    public void signingOfDocuments(@RequestBody ApplicationEntity applicationEntity) {
        code.sesCode(applicationEntity);
    }

}