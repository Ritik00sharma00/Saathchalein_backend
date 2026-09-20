package com.saathchalein_backend.saathchalein_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class JwtService {

    private final String jwtSecret;

    public JwtService(@Value("${jwt.secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String generateToken(String email) {
        return Base64.getEncoder().encodeToString((jwtSecret + ":" + email + ":" + UUID.randomUUID()).getBytes());
    }

    public String extractEmail(String token) {
        String decoded = new String(Base64.getDecoder().decode(token));
        String[] parts = decoded.split(":");
        if (parts.length >= 3) {
            return parts[1];
        }
        return null;
    }
}
