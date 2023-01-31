package ru.podgoretskaya.deal.dto;

import lombok.*;
import ru.podgoretskaya.deal.entity_enum.Gender;
import ru.podgoretskaya.deal.entity_enum.MaritalStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinishRegistrationRequestDTO {
    private Gender gender;
    private MaritalStatus maritalStatus;
    private Integer dependentAmount;
    private LocalDate passportIssueDate;
    private String passportIssueBranch;
    private EmploymentDTO employment;
    private String account;

}