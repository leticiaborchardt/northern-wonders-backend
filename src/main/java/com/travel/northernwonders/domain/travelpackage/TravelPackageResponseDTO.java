package com.travel.northernwonders.domain.travelpackage;

import com.travel.northernwonders.domain.destination.Destination;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TravelPackageResponseDTO(
        String id,
        Destination destination,
        String name,
        String description,
        String hotelName,
        String airline,
        String flightNumber,
        BigDecimal price,
        LocalDate startDate,
        LocalDate endDate
) {
}
