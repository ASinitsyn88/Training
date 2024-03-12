package com.example.training.config;

import com.example.training.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private static final int JWT_TOKEN_BEGIN_INDEX = 7;

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(JWT_TOKEN_BEGIN_INDEX);
        var dbToken = tokenRepository.findByToken(jwt).orElse(null);
        if (dbToken != null) {
            dbToken.setExpired(true);
            dbToken.setRevoked(true);
            tokenRepository.save(dbToken);
            SecurityContextHolder.clearContext();
        }
    }
}