package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.PaymentScheduleElement;
import ru.podgoretskaya.deal.entity_enum.CreditStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Credit")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class CreditEntity {
    @Id
    @SequenceGenerator(name = "CreditGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CreditGenerator")
    @Column(name = "credit_id", nullable = false)
    private Long clientID;
    private BigDecimal amount;
    private int term;
    @Column(name = "monthly_payment")
    private BigDecimal monthPayment;
    private BigDecimal rate;
    private BigDecimal psk;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", name = "payment_schedule")
    private List<PaymentScheduleElement> paymentSchedule;
    @Column(name = "insurance_enable")
    private boolean insuranceEnabled;
    @Column(name = "salary_client")
    private boolean salaryClient;
    @Enumerated(EnumType.STRING)
    @Column(name = "credit_status")
    private CreditStatus creditStatus;

}
