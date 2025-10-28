package com.findash.account_service.service;

import org.springframework.stereotype.Service;

import java.security.Key;

import org.springframework.beans.factory.annotation.Value;

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
}
