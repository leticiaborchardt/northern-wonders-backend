package com.travel.northernwonders.domain.customer;

import com.travel.northernwonders.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "customers")
@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private User user;

    private String name;
    private String phone;
    private LocalDate birthDate;
    private String documentNumber;
    private String address;

    public Customer(User user, String name, String phone, LocalDate birthDate, String documentNumber, String address) {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.documentNumber = documentNumber;
        this.address = address;
    }
}
