package com.example.training.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * See JwtService on jwt_mechanism schema
 */
@Service
public class JwtService {
    @Value("${security.jwt.secret}")
    private String sha256SecretKey;
    @Value("${security.jwt.expiration-in-ms}")
    private long validityInMilliseconds;

    public String generateTokenByUserEmail(String userEmail) {
        return generateTokenByClaimsAndUserEmail(new HashMap<>(), userEmail);
    }

    private String generateTokenByClaimsAndUserEmail(Map<String, Object> extraClaims, String userEmail) {
        return Jwts
                .builder()
                .claims().empty().add(extraClaims)
                .and()
                .subject(userEmail)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(getSecretSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmailFromToken(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUserEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sha256SecretKey));
    }
}