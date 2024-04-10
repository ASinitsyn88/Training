package com.example.training.auth;

import com.example.training.config.JwtService;
import com.example.training.token.Token;
import com.example.training.token.TokenRepository;
import com.example.training.user.User;
import com.example.training.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import static com.example.training.token.TokenType.BEARER;
import static com.example.training.user.Role.USER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {
    @Mock private JwtService jwtService;
    @Mock private UserRepository repository;
    @Mock private TokenRepository tokenRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private AuthenticationManager authenticationManager;
    private AuthenticationService underTest;

    @BeforeEach
    void setUp() {
        this.underTest = new AuthenticationService(jwtService, repository, tokenRepository, passwordEncoder, authenticationManager);
    }

    @Test
    void registerTest() {
        // Given
        RegisterRequest request = new RegisterRequest("Test", "Testov", "test@gmail.com", "pwd123", USER);
        String encodedPassword = "$2a$10$6qyiS0JrD.NMv5M.Y7VmOes69cfhLdvSC2Pe1IPBFLOHhdVsw2Jc6";
        String jwtToken = "jwt_token";
        String refreshToken = "refresh_token";
        var dbUser = User.builder()
                .id(1)
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(encodedPassword)
                .role(request.getRole())
                .build();
        var token = Token.builder()
                .user(dbUser)
                .token(jwtToken)
                .tokenType(BEARER)
                .expired(false)
                .revoked(false)
                .build();
        AuthenticationResponse expectedResponse = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

        // Mock the calls
        when(passwordEncoder.encode(request.getPassword())).thenReturn(encodedPassword);
        when(repository.save(any())).thenReturn(dbUser);
        when(repository.save(any())).thenReturn(dbUser);
        when(jwtService.generateTokenByUserEmail(request.getEmail())).thenReturn(jwtToken);
        when(jwtService.generateRefreshTokenByUserEmail(request.getEmail())).thenReturn(refreshToken);

        // When
        AuthenticationResponse response = underTest.register(request);

        // Then
        ArgumentCaptor<Token> tokenArgumentCaptor = ArgumentCaptor.forClass(Token.class);
        verify(tokenRepository).save(tokenArgumentCaptor.capture());
        Token capturedToken = tokenArgumentCaptor.getValue();

        assertEquals(token, capturedToken);
        assertEquals(expectedResponse, response);
    }
}