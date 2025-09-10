package com.taskmanagement.salesflowx.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class GenerateJwtKey {
    static void main() {
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();
        String baseKey= Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated JWT Key: " + baseKey);

    }
}
