package ru.podgoretskaya.deal.dto;

import lombok.*;
import ru.podgoretskaya.deal.entityEnum.EmploymentPosition;
import ru.podgoretskaya.deal.entityEnum.EmploymentStatus;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentDTO {
    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private EmploymentPosition position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;
}