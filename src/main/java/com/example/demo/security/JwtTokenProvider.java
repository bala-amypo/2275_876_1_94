package com.example.demo.security;

import org.springframework.security.core.Authentication;
import java.util.Base64;

public class JwtTokenProvider {

    private final String secret;

    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    public String generateToken(Authentication auth, Long id, String role, String email) {
        return Base64.getEncoder().encodeToString(
                (email + ":" + role + ":" + id + ":" + secret).getBytes());
    }
}
