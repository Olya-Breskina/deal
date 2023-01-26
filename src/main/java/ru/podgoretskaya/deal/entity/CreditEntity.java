package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.PaymentScheduleElement;
import ru.podgoretskaya.deal.entityEnum.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Credit")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class CreditEntity {
    @Id
    @SequenceGenerator(name = "CreditGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clientID;
    private BigDecimal amount;
    private int term;
    private BigDecimal monthPayment;
    private BigDecimal rate;
    private BigDecimal psk;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
     private List<PaymentScheduleElement> paymentSchedule;

    private boolean insuranceEnabled;
    private boolean salaryClient;
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;

}
