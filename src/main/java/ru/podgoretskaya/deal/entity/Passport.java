package ru.podgoretskaya.deal.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passportID;
    private String series;
    private String number;
    private LocalDate issueDate;
    private String issueBranch;
}
