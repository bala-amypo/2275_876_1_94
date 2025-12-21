package com.example.demo.security;

public class JwtTokenProvider {

    private final String secret;

    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }
}
