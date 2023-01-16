package ru.podgoretskaya.deal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Schema(description = "предварительные данные по кредиту")
public class LoanOfferDTO {
    @Schema(description = "id в бд")
    private Long applicationId;
    @Schema(description = "сумма кредита")
    private BigDecimal requestedAmount;
    @Schema(description = "итоговый платеж")
    private BigDecimal totalAmount;
    @Schema(description = "срок кредита")
    private Integer term;
    @Schema(description = "ежемесячный платеж")
    private BigDecimal monthlyPayment;
    @Schema(description = "ставка")
    private BigDecimal rate;
    @Schema(description = "страхование кредита")
    private Boolean isInsuranceEnabled;
    @Schema(description = "зарплатный клиент")
    private Boolean isSalaryClient;

}