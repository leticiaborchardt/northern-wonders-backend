package com.travel.northernwonders.domain.customer;

import com.travel.northernwonders.domain.user.UserDTO;

public record CustomerUserDTO(UserDTO user, CustomerDTO customer) {
}
