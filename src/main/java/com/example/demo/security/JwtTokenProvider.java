package com.example.demo.security;

import com.example.demo.model.User;

public class JwtTokenProvider {

    private final String secret = "test-secret";

    public String generateToken(User user) {
        return user.getEmail() + "-token";
    }
}
