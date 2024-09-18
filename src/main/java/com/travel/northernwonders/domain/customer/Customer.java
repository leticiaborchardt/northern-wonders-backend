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
}
