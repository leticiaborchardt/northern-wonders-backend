package com.travel.northernwonders.domain.booking;

import com.travel.northernwonders.domain.customer.Customer;
import com.travel.northernwonders.domain.travelpackage.TravelPackage;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "bookings")
@Entity()
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private TravelPackage travelPackage;

    private Integer numberOfPeople;
    private String paymentMethod;
    private BigDecimal paymentAmount;
    private PaymentStatus paymentStatus;
    private LocalDate bookingDate;

    public Booking(Customer customer, TravelPackage travelPackage, Integer numberOfPeople, String paymentMethod, BigDecimal paymentAmount, LocalDate bookingDate) {
        this.customer = customer;
        this.travelPackage = travelPackage;
        this.numberOfPeople = numberOfPeople;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
        this.bookingDate = bookingDate;
    }
}
