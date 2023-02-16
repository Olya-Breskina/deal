package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.LocalTimeType;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.dto.StatusHistory;
import ru.podgoretskaya.deal.entity_enum.ApplicationStatus;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Application")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)

public class ApplicationEntity {
    @Id
    @SequenceGenerator(name = "ApplicationGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ApplicationGenerator")
    @Column(name = "application_id", nullable = false)
    private Long applicationID;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @OneToOne
    @JoinColumn(name = "credit_id")
    private CreditEntity credit;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Column(name = "creation_date")
    private LocalTimeType creationDate;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private LoanOfferDTO appliedOffer;

    private String sesCode;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private List<StatusHistory> statusHistory;

}
