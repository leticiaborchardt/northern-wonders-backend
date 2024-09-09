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

    private String region;
    private String city;

    public Destination(String region, String city) {
        this.region = region;
        this.city = city;
    }
}
