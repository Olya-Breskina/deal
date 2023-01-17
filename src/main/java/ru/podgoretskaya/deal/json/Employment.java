package ru.podgoretskaya.deal.json;


import ru.podgoretskaya.deal.entityEnum.EmploymentPosition;
import ru.podgoretskaya.deal.entityEnum.EmploymentStatus;

import java.math.BigDecimal;

public class Employment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long employmentID;
    private EmploymentStatus status;
    private String employmentInn;
    private BigDecimal salary;
    private EmploymentPosition position;
    private int workExperienceTotal;
    private int workExperienceCurrent;
}

