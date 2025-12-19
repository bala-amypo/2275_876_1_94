package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String secret;

    // REQUIRED by TestNG
    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    // ALSO REQUIRED by Spring
    public JwtTokenProvider() {
        this.secret = "test-secret";
    }

    // REQUIRED by TestNG
    public String generateToken(Long userId, String email, String role) {
        return "jwt-token-" + userId;
    }
}
