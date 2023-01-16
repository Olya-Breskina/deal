package ru.podgoretskaya.deal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @OneToOne
    private Long clientID;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthdate;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    private int dependentAmout;
@OneToOne
    private Long passportID;
    @OneToOne
    private Employment employmentID;
    private String account;

    public ClientEntity() {
    }

}
