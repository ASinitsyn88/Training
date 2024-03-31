package com.example.training.config;

import com.example.training.token.TokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import static com.example.training.token.TokenUtil.JWT_TOKEN_BEGIN_INDEX;
import static com.example.training.token.TokenUtil.JWT_TOKEN_BEGIN_PART;

/**
 * See JwtAuthFilter on jwt_mechanism.PNG
 * Designed to process all requests except /register, /authenticate, /refresh-token, /logout
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // do not authenticate the user who is going to register or authenticate
        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt;
        final String userEmail;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(JWT_TOKEN_BEGIN_PART)) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(JWT_TOKEN_BEGIN_INDEX);
        try {
            userEmail = jwtService.extractUserEmailFromToken(jwt);
        } catch (ExpiredJwtException e) {
            tokenRepository.findByToken(jwt).ifPresent(dbToken -> {
                dbToken.setExpired(true);
                tokenRepository.save(dbToken);
            });
            return;
        }
        // If user is not authenticated
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Token validation on the database side. Logout verification
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map(t -> !t.isExpired() && !t.isRevoked())
                    .orElse(false);
            var userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.doesTokenBelongToUser(jwt, userDetails) && isTokenValid) {
                // Updating the SecurityContextHolder. Tell to spring that the user is authenticated
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}