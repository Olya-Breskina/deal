package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entityEnum.ApplicationStatus;
import ru.podgoretskaya.deal.json.StatusHistory;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "Application")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)

public class ApplicationEntity {
    @Id
    @SequenceGenerator(name = "ApplicationGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long applicationID;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private LocalTime creation_date;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private LoanOfferDTO appliedOffer;

    private String sesCode;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private StatusHistory statusHistiry;

}
