package com.campuscache.campus_cache.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    
    // 🔐 Secret key (later env variable me le jaayenge)
    private static final String SECRET = "my_super_secret_key_which_should_be_long";
    private static final long EXPIRY_TIME = 1000 * 60 * 60; // 1 hour

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 1️⃣ TOKEN GENERATE
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)                 // payload
                .setIssuedAt(new Date())           // issue time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
     // 2️⃣ TOKEN SE EMAIL NIKALNA (verify bhi yahin hota hai)
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)             // verify signature + expiry
                .getBody()
                .getSubject();
    }
}
