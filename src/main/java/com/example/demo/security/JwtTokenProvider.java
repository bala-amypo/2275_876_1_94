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

@Component
public class JwtTokenProvider {

    private final String jwtSecret = "secretkey123";
    private final long jwtExpirationMs = 86400000;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // ADD THIS
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ADD THIS
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
