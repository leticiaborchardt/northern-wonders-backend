package com.travel.northernwonders.controllers;

import com.travel.northernwonders.domain.user.AuthDTO;
import com.travel.northernwonders.domain.user.LoginResponseDTO;
import com.travel.northernwonders.domain.user.RegisterDTO;
import com.travel.northernwonders.domain.user.User;
import com.travel.northernwonders.infra.security.TokenService;
import com.travel.northernwonders.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(new LoginResponseDTO(user.getId(), user.getLogin(), user.getRole().getRole(), tokenService.generateToken(user)));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (this.userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        this.userRepository.save(new User(data.login(), encryptedPassword, data.role()));

        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = tokenService.extractTokenFromRequest(request);
        tokenService.addTokenToBlacklist(token);

        return ResponseEntity.ok().build();
    }
}
