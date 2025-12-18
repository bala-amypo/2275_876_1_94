// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import java.util.Date;

// public class JwtTokenProvider {

//     private final String secret;
//     private final long expiration = 86400000; // 1 day

//     public JwtTokenProvider(String secret) {
//         this.secret = secret;
//     }

//     public String generateToken(Long userId,String email,String role) {

//         return Jwts.builder().setSubject(email).claim("userId", userId).claim("role", role).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + expiration)).signWith(SignatureAlgorithm.HS256, secret).compact();
//     }
// }









package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "secretkey123";
    private final long jwtExpirationMs = 86400000;

    // ✅ USED BY CONTROLLER
    public String generateToken(Long userId, String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        return true;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
