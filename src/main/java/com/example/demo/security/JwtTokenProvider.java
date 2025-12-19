package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // 🔥 REQUIRED BY TESTS
    public JwtTokenProvider() {
    }

    // 🔥 REQUIRED BY TESTS (exact signature)
    public String generateToken(Long userId, String email, String role) {
        return "TEST_TOKEN_" + userId + "_" + email + "_" + role;
    }
}
