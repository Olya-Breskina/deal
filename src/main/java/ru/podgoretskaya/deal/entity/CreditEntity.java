package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.PaymentScheduleElement;
import ru.podgoretskaya.deal.entity_enum.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Credit")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class CreditEntity {
    @Id
    @SequenceGenerator(name = "CreditGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
