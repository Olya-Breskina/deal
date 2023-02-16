package ru.podgoretskaya.deal.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.podgoretskaya.deal.dto.EmploymentDTO;
import ru.podgoretskaya.deal.dto.Passport;
import ru.podgoretskaya.deal.entity_enum.Gender;
import ru.podgoretskaya.deal.entity_enum.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Client")

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ClientEntity {
    @Id
    @SequenceGenerator(name = "ClientGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientGenerator")
    @Column(name = "client_id", nullable = false)
    private Long clientID;
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate birthdate;
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    @Column(name = "dependent_amount")
    private int dependentAmount;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Passport passport;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private EmploymentDTO employment;
    private String account;

}
