package ru.podgoretskaya.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class EmploymentDTO {
    private EmploymentStatus employmentStatus;
    private String employerINN;
    private BigDecimal salary;
    private Position position;
    private Integer workExperienceTotal;
    private Integer workExperienceCurrent;

    public enum EmploymentStatus {
        UNEMPLOYED, SELF_EMPLOYED, EMPLOYED, BUSINESS_OWNER
    }

    public enum Position {
        WORKER, MID_MANAGER, TOP_MANAGER, OWNER
    }
}