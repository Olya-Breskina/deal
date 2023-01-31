package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.LoanOfferDTO;
import ru.podgoretskaya.deal.entity_enum.ApplicationStatus;
import ru.podgoretskaya.deal.dto.StatusHistory;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationEntity that = (ApplicationEntity) o;
        return Objects.equals(applicationID, that.applicationID) && Objects.equals(client, that.client) && Objects.equals(credit, that.credit) && status == that.status && Objects.equals(creation_date, that.creation_date) && Objects.equals(appliedOffer, that.appliedOffer) && Objects.equals(sesCode, that.sesCode) && Objects.equals(statusHistiry, that.statusHistiry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationID, client, credit, status, creation_date, appliedOffer, sesCode, statusHistiry);
    }
}
