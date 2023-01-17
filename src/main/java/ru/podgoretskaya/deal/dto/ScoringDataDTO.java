package ru.podgoretskaya.deal.dto;

import lombok.Getter;

import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString

public class ScoringDataDTO {
    private BigDecimal amount;
    private Integer term;
    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;//пол
    private LocalDate birthdate;
    private String passportSeries;
    private String passportNumber;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;//зависимая сумма
    private EmploymentDTO employment;
    private String account;
    private Boolean isInsuranceEnabled;
    private Boolean isSalaryClient;

    public enum Gender {
        MALE, FEMALE, NOT_BINARY
    }

    public enum MaritalStatus {
        MARRIED, DIVORCED, SINGLE, WIDOW_WIDOWER
    }
}