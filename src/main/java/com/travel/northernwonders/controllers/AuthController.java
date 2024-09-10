package com.travel.northernwonders.controllers;

import com.travel.northernwonders.domain.user.AuthDTO;
import com.travel.northernwonders.domain.user.LoginResponseDTO;
import com.travel.northernwonders.domain.user.User;
import com.travel.northernwonders.domain.user.UserDTO;
import com.travel.northernwonders.infra.security.TokenService;
import com.travel.northernwonders.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(new LoginResponseDTO(tokenService.generateToken(user)));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserDTO data) {
        try {
            userService.createUser(data);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String token = tokenService.extractTokenFromRequest(request);
        tokenService.addTokenToBlacklist(token);

        return ResponseEntity.ok().build();
    }
}
