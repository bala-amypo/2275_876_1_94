package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // ✅ REQUIRED: NO-ARG CONSTRUCTOR
    public JwtTokenProvider() {
    }

    // ✅ REQUIRED SIGNATURE
    public String generateToken(Long userId, String username, String role) {
        return "TEST_TOKEN_" + userId + "_" + username + "_" + role;
    }
}
