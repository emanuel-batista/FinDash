package com.findash.account_service.service;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
    @Value("${application.jwt.secret-key}")
    private String secretKey;

    @Value("${application.jwt.expiration-ms}")
    private long expirationTime;

    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username){
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expirationDate)
            .signWith(this.key)
            .compact();
    }
}
