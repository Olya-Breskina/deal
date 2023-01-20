package ru.podgoretskaya.deal.json;


import lombok.Data;

import java.time.LocalDate;
@Data
public class Passport {

    private String series;
    private String number;
    private LocalDate issueDate;
    private String issueBranch;
}
