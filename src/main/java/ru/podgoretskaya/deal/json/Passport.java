package ru.podgoretskaya.deal.json;


import java.time.LocalDate;

public class Passport {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passportID;
    private String series;
    private String number;
    private LocalDate issueDate;
    private String issueBranch;
}
