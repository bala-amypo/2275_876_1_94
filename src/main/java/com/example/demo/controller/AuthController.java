package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService userService,JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {

        User user = userService.registerUser(
                request.getFullName(),
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(
                new AuthResponse(
                        token,
                        user.getId(),
                        user.getEmail(),
                        user.getRole()
                )
        );
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        User user = userService.getByEmail(request.getEmail());

        // password check happens in service / encoder
        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return ResponseEntity.ok(
                new AuthResponse(
                        token,
                        user.getId(),
                        user.getEmail(),
                        user.getRole()
                )
        );
    }
}