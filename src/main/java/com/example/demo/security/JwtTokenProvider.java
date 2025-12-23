package com.example.demo.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtTokenProvider {
    private final SecretKey jwtSecretKey;
    private final long jwtExpirationMs = 3600000; // 1 hour default

    public JwtTokenProvider(String secret) {
        // For HS256, key must be at least 256 bits (32 bytes)
        this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Authentication authentication, Long userId, String role, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .claim("role", role)
                .claim("email", email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
