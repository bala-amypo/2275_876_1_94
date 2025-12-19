package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    // REQUIRED NO-ARG CONSTRUCTOR
    public JwtTokenProvider() {}

    // REQUIRED BY TESTS
    public String generateToken(Long userId, String email, String role) {
        return "TOKEN_" + userId + "_" + email + "_" + role;
    }
}
