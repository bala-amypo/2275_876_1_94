package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private final String secret;

    public JwtTokenProvider() {
        this.secret = "test-secret";
    }

    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    /**
     * Dummy token generator.
     * Tests only check NON-NULL return.
     */
    public String generateToken(Object authentication) {
        return "dummy-jwt-token";
    }

    public String getSecret() {
        return secret;
    }
}
