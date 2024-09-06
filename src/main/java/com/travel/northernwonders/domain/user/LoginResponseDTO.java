package com.travel.northernwonders.domain.user;

public record LoginResponseDTO(
        String id,
        String email,
        String role,
        String token
) {
}
