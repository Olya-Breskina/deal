package ru.podgoretskaya.deal.json;

import ru.podgoretskaya.deal.entityEnum.ChangeType;

import java.time.LocalTime;

public class StatusHistory {
    private String status;
    private LocalTime time;
    private ChangeType changeType;
}
