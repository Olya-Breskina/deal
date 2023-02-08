package ru.podgoretskaya.deal.util;

import ru.podgoretskaya.deal.dto.StatusHistory;
import ru.podgoretskaya.deal.entity.ApplicationEntity;
import ru.podgoretskaya.deal.entity_enum.ApplicationStatus;
import ru.podgoretskaya.deal.entity_enum.ChangeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static ru.podgoretskaya.deal.entity_enum.ChangeType.AUTOMATIC;

public class HistiryManagerUtil {
    public static ApplicationEntity updateStatus(ApplicationEntity applicationEntity, ApplicationStatus newStatus){
        applicationEntity.setStatus(newStatus);
        List<StatusHistory> historyStatuses=applicationEntity.getStatusHistory()==null
                ? new ArrayList<>(): applicationEntity.getStatusHistory();
        historyStatuses.add(new StatusHistory(newStatus, LocalDateTime.now(), AUTOMATIC));
        applicationEntity.setStatusHistory(historyStatuses);
    return applicationEntity;
    }
}
