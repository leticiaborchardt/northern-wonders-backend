package com.travel.northernwonders.domain.travelpackage;

import com.travel.northernwonders.domain.destination.Destination;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TravelPackageDTO(
        @NotNull Destination destination,
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String imageUrl,
        String hotelName,
        String airline,
        String flightNumber,
        @NotBlank BigDecimal price,
        @NotBlank LocalDate startDate,
        @NotBlank LocalDate endDate
) {
}
