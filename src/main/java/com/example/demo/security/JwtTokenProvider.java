package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {}

    public String generateToken(Long userId, String name, String email) {
        return "TOKEN_" + userId + "_" + name + "_" + email;
    }
}
