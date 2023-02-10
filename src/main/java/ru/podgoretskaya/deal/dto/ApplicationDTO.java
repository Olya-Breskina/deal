package ru.podgoretskaya.deal.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.entity.ClientEntity;
import ru.podgoretskaya.deal.entity.CreditEntity;
import ru.podgoretskaya.deal.entity_enum.ApplicationStatus;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

    private Long applicationID;
//    private ClientEntity client; //должны быть ДТО
//    private CreditEntity credit;
    private ApplicationStatus status;
    private LocalTime creationDate;
    private LoanOfferDTO appliedOffer;
    private String sesCode;
    private List<StatusHistory> statusHistory;
}
