package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.entityEnum.Gender;
import ru.podgoretskaya.deal.entityEnum.MaritalStatus;
import ru.podgoretskaya.deal.json.Employment;
import ru.podgoretskaya.deal.json.Passport;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Client")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ClientEntity {
    @Id
    @SequenceGenerator(name = "ClientGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Passport passport;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Employment employmentID;

    private String account;

}
