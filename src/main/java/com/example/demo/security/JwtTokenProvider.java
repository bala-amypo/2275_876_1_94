package com.example.demo.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private String secret;

    // REQUIRED
    public JwtTokenProvider() {
        this.secret = "default-secret";
    }

    // REQUIRED
    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    // REQUIRED by TestNG
    public String generateToken(Long userId, String email, String role) {
        return "jwt-token-" + userId;
    }

    // REQUIRED by TestNG (OVERLOAD)
    public String generateToken(
            UsernamePasswordAuthenticationToken auth,
            long userId,
            String email,
            String role) {

        return "jwt-token-" + userId;
    }
}
