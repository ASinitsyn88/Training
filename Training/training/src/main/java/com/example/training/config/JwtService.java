package com.example.training.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
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
    // TODO move to application.yml
    private static final String HMAC_SHA_256_SECRET_KEY = "2691e6772bb26f35efae6797cc1422644dfd26bcec748042875095aef4a3f27b";

    // TODO pass email instead of UserDetails
    public String generateTokenByUserEmail(UserDetails userDetails) {
        return generateTokenByClaimsAndUserEmail(new HashMap<>(), userDetails);
    }

    // TODO pass email instead of UserDetails
    private String generateTokenByClaimsAndUserEmail(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .claims().empty().add(extraClaims)
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
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
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(HMAC_SHA_256_SECRET_KEY));
    }
}