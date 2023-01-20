package ru.podgoretskaya.deal.json;

import lombok.Data;
import ru.podgoretskaya.deal.entityEnum.ChangeType;

import java.time.LocalTime;
@Data
public class StatusHistory {
    private String status;
    private LocalTime time;
    private ChangeType changeType;
}
