package com.travel.northernwonders.domain.destination;

import jakarta.validation.constraints.NotBlank;

public record DestinationDTO(@NotBlank String region, @NotBlank String city) {
}
