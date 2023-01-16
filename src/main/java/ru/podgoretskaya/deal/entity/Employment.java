package ru.podgoretskaya.deal.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class Employment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employmentID;
    private EmploymentStatus status;
    private String employmentInn;
    private BigDecimal salary;
    private EmploymentPosition position;
    private int workExperienceTotal;
    private int workExperienceCurrent;
}

