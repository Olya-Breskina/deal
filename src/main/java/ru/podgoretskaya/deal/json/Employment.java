package ru.podgoretskaya.deal.json;


import lombok.Data;
import ru.podgoretskaya.deal.entityEnum.EmploymentPosition;
import ru.podgoretskaya.deal.entityEnum.EmploymentStatus;

import java.math.BigDecimal;
@Data
public class Employment {

    private EmploymentStatus status;
    private String employmentInn;
    private BigDecimal salary;
    private EmploymentPosition position;
    private int workExperienceTotal;
    private int workExperienceCurrent;
}

