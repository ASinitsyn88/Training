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
 * See JwtService on jwt_mechanism.PNG
 */
@Service
public class JwtService {
    // It is a secret cryptographic key used to sign and verify JWT
    @Value("${security.jwt.secret}")
    private String sha256SecretKey;
    @Value("${security.jwt.expiration-in-ms}")
    private long jwtExpirationInMs;
    @Value("${security.jwt.refresh-token.expiration-in-ms}")
    private long refreshExpirationInMs;

    public String generateTokenByUserEmail(String userEmail) {
        return generateTokenByClaimsAndUserEmail(new HashMap<>(), userEmail, jwtExpirationInMs);
    }

    public String generateRefreshTokenByUserEmail(String userEmail) {
        return generateTokenByClaimsAndUserEmail(new HashMap<>(), userEmail, refreshExpirationInMs);
    }

    public boolean doesTokenBelongToUser(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmailFromToken(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public String extractUserEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private String generateTokenByClaimsAndUserEmail(Map<String, Object> extraClaims, String userEmail, long expirationInMs) {
        return Jwts
                .builder()
                .claims().empty().add(extraClaims)
                .and()
                .subject(userEmail)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationInMs))
                .signWith(getSecretSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        var parser = Jwts.parser().verifyWith(getSecretSignInKey()).build();
        return parser.parseSignedClaims(token).getPayload();
    }

    private SecretKey getSecretSignInKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sha256SecretKey));
    }
}