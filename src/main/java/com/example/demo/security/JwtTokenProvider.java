package com.example.demo.security;

public class JwtTokenProvider {

    private final String secret;

    // ⚠️ REQUIRED by test suite
    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}
