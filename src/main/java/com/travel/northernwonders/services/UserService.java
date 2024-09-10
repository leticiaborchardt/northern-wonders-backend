package com.travel.northernwonders.services;

import com.travel.northernwonders.domain.user.User;
import com.travel.northernwonders.domain.user.UserDTO;
import com.travel.northernwonders.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDTO user) {
        if (userRepository.findByLogin(user.login()) != null) {
            throw new IllegalArgumentException("User already exists with this login.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());

        return userRepository.save(new User(user.login(), encryptedPassword, user.role()));
    }
}
