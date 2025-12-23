package com.example.demo.security;

import org.springframework.security.core.Authentication;

public class JwtTokenProvider {

    private final String secret;

    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    public String generateToken(Authentication auth, Long userId, String role, String email) {
        return "JWT_" + userId + "_" + role + "_" + email + "_" + System.currentTimeMillis();
    }
}
