package com.example.training.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(value = "security.jwt")
public record JwtConfig(
        // It is a secret cryptographic key used to sign and verify JWT
        String secret,
        long expirationInMs,
        @NestedConfigurationProperty RefreshToken refreshToken
) {

}

@ConfigurationProperties(value = "security.jwt.refresh-token")
record RefreshToken(
        long expirationInMs
) {

}