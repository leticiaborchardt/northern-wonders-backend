package com.travel.northernwonders.domain.destination;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.travel.northernwonders.domain.travelpackage.TravelPackage;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @JsonBackReference
    @OneToMany(mappedBy = "destination")
    private List<TravelPackage> travelPackages;

    public Destination(String region, String city) {
        this.region = region;
        this.city = city;
    }
}
