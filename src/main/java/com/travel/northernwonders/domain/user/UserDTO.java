package com.travel.northernwonders.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotBlank String login, @NotBlank String password, @NotNull UserRole role) {
}
