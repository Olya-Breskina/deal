package ru.podgoretskaya.deal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.podgoretskaya.deal.entity_enum.ApplicationStatus;
import ru.podgoretskaya.deal.entity_enum.ChangeType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StatusHistory {
    private ApplicationStatus status;
    private LocalDateTime time;
    private ChangeType changeType;
}
