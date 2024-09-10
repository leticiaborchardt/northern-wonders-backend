package com.travel.northernwonders.domain.customer;

import com.travel.northernwonders.domain.user.UserResponseDTO;

import java.time.LocalDate;

public record CustomerResponseDTO(
        String id,
        UserResponseDTO user,
        String name,
        String phone,
        LocalDate birthDate,
        String documentNumber,
        String address
) {
}
