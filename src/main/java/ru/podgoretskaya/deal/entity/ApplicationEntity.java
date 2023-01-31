package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity_enum.ApplicationStatus;
import ru.podgoretskaya.deal.dto.StatusHistory;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Application")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)

public class ApplicationEntity {
    @Id
    @SequenceGenerator(name = "ApplicationGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ApplicationGenerator")
    private Long applicationID;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalTime creationDate;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private LoanOfferDTO appliedOffer;

    private String sesCode;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<StatusHistory> statusHistory;

}
