package ru.podgoretskaya.deal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@Entity
@Table(name = "Credit")
public class CreditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne
    private Long clientID;
    private BigDecimal amount;
    private int term;
    private BigDecimal monthPayment;
    private BigDecimal rate;
    private BigDecimal psk;
    private int paymentSchedule;
    private boolean insuranceEnabled;
    private boolean salaryClient;
    @Enumerated(EnumType.STRING)
    private CreditStatus creditStatus;
}
