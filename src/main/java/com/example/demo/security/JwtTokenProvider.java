package com.example.demo.security;

import io.jsonwebtoken.*;
import java.util.Date;

public class JwtTokenProvider {

    private final String secret;
    private final long expiration = 86400000; // 1 day

    public JwtTokenProvider(String secret) {
        this.secret = secret;
    }

    public String generateToken(Long userId,String email,String role) {

        return Jwts.builder().setSubject(email).claim("userId", userId).claim("role", role).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
}