package com.travel.northernwonders.domain.travelpackage;

import com.travel.northernwonders.domain.destination.Destination;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "travel_packages")
@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Destination destination;

    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String imageUrl;

    private String hotelName;

    private String airline;

    private String flightNumber;

    private BigDecimal price;

    private LocalDate startDate;

    private LocalDate endDate;
}
