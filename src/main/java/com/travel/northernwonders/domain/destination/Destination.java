package com.travel.northernwonders.domain.destination;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "destinations")
@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String country;
    private String city;

    public Destination(String country, String city) {
        this.country = country;
        this.city = city;
    }
}
