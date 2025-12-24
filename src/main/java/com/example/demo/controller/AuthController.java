package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    public AuthController(UserService userService,
                          JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public User register(@RequestBody AuthRequest req) {
        return userService.registerUser("User", req.email, req.password);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest req) {
        User user = userService.getByEmail(req.email);
        String token = tokenProvider.generateToken(user);
        return new AuthResponse(token, user.getId(),
                user.getEmail(), user.getRole());
    }
}
