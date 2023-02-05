package ru.podgoretskaya.deal.dto;

import lombok.Data;

@Data
public class EmailMessage {
    private String address;
    private Theme theme;
    private Long applicationId;
}