package com.example.training.auth;

import com.example.training.config.JwtService;
import com.example.training.token.Token;
import com.example.training.token.TokenRepository;
import com.example.training.user.User;
import com.example.training.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;
import static com.example.training.token.TokenType.BEARER;
import static com.example.training.token.TokenUtil.JWT_TOKEN_BEGIN_INDEX;
import static com.example.training.token.TokenUtil.JWT_TOKEN_BEGIN_PART;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        log.info("register user: {}", user.getEmail());
        var dbUser = repository.save(user);
        var jwt = jwtService.generateTokenByUserEmail(user.getEmail());
        var refreshToken = jwtService.generateRefreshTokenByUserEmail(user.getEmail());
        saveUserToken(dbUser, jwt);
        return AuthenticationResponse.builder().accessToken(jwt).refreshToken(refreshToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.info("authentication by email: {}", request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var dbUser = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwt = jwtService.generateTokenByUserEmail(dbUser.getEmail());
        var refreshToken = jwtService.generateRefreshTokenByUserEmail(dbUser.getEmail());
        revokeAllUserTokens(dbUser);
        saveUserToken(dbUser, jwt);
        return AuthenticationResponse.builder().accessToken(jwt).refreshToken(refreshToken).build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String refreshToken;
        final String userEmail;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith(JWT_TOKEN_BEGIN_PART)) {
            return;
        }
        refreshToken = authHeader.substring(JWT_TOKEN_BEGIN_INDEX);
        userEmail = jwtService.extractUserEmailFromToken(refreshToken);
        log.info("email {} extracted from token {}", userEmail, refreshToken);
        if (userEmail != null) {
            var user = repository.findByEmail(userEmail).orElseThrow();
            if (jwtService.doesTokenBelongToUser(refreshToken, user)) {
                // We must keep the same refresh-token and generate new access-token
                var accessToken = jwtService.generateTokenByUserEmail(user.getEmail());
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(User user, String jwt) {
        var token = Token.builder()
                .user(user)
                .token(jwt)
                .tokenType(BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}