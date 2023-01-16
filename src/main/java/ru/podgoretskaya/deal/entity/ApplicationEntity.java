package ru.podgoretskaya.deal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
@Getter
@Setter
@Entity
@Table(name = "Application")
public class ApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String applicationID;
@OneToOne
    private Long clientID;
    @OneToOne
    private Long creditID;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    private LocalTime creation_date;
    private int appliedOffer;
    private LocalTime sesCode;
    @OneToOne
    private StatusHistory statusHistiry;

}
