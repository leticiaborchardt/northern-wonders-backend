package com.travel.northernwonders.domain.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDTO(
        @NotBlank String name,
        @NotBlank String phone,
        @NotNull LocalDate birthDate,
        @NotBlank String documentNumber,
        @NotBlank String address
) {
}
