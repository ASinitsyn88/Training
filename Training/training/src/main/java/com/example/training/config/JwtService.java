package com.example.training.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties({JwtConfig.class, RefreshToken.class})
@Service
public class JwtService {
    // It is a secret cryptographic key used to sign and verify JWT
    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateTokenByUserEmail(String userEmail) {
        return generateTokenByClaimsAndUserEmail(new HashMap<>(), userEmail, jwtConfig.expirationInMs());
    }

    public String generateRefreshTokenByUserEmail(String userEmail) {
        return generateTokenByClaimsAndUserEmail(new HashMap<>(), userEmail, jwtConfig.refreshToken().expirationInMs());
    }

    public boolean doesTokenBelongToUser(String token, UserDetails userDetails) {
        final String userEmail = extractUserEmailFromToken(token);
        return (userEmail.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public String extractUserEmailFromToken(String token) {
        if (token == null) {
            throw new IllegalArgumentException("token is null");
        }
        return extractClaim(token, Claims::getSubject);
    }

    private String generateTokenByClaimsAndUserEmail(Map<String, Object> extraClaims, String userEmail, long expirationInMs) {
        if (userEmail == null || userEmail.trim().isEmpty()) {
            throw new IllegalArgumentException("email is empty");
        }
        if (expirationInMs <= 0) {
            throw new IllegalArgumentException("expirationInMs cannot be less than or equal to zero");
        }
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
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret()));
    }
}