package com.example.training.config;

import com.example.training.token.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import static com.example.training.token.TokenUtil.JWT_TOKEN_BEGIN_INDEX;
import static com.example.training.token.TokenUtil.JWT_TOKEN_BEGIN_PART;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String jwt;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(JWT_TOKEN_BEGIN_PART)) {
            return;
        }
        jwt = authHeader.substring(JWT_TOKEN_BEGIN_INDEX);
        var dbToken = tokenRepository.findByToken(jwt).orElse(null);
        log.info("logout for token: {}", dbToken);
        if (dbToken != null) {
            dbToken.setExpired(true);
            dbToken.setRevoked(true);
            tokenRepository.save(dbToken);
            SecurityContextHolder.clearContext();
        }
    }
}