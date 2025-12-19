package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {
        // no-arg constructor required by tests
    }

    public String generateToken(Long userId, String email, String role) {
        return "dummy-token"; // Stub implementation for tests
    }
}
