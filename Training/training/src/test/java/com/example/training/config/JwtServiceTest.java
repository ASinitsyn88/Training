package com.example.training.config;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtServiceTest {
    private static String sha256SecretKey;
    private static long jwtExpirationInMs;
    private static long refreshExpirationInMs;

    @BeforeAll
    static void setUp() {
        sha256SecretKey = "2691e6772bb26f35efae6797cc1422644dfd26bcec748042875095aef4a3f27b";
        jwtExpirationInMs = 604800;
        refreshExpirationInMs = 604800000;
    }

    @Test
    void generateTokenByUserEmail_NotNull_Test() {
        // Given
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        // When
        String token = jwtService.generateTokenByUserEmail("myemail@gmail.com");
        // Then
        assertNotNull(token);
    }

    @Test
    void generateTokenByUserEmail_Null_Test() {
        // Given
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        // When
        String errMsg = assertThrows(IllegalArgumentException.class, () -> jwtService.generateTokenByUserEmail(null)).getMessage();
        // Then
        assertEquals("email is empty", errMsg);
    }

    @Test
    void generateTokenByUserEmail_Expiration_Zero_Test() {
        // Given
        JwtService jwtService = new JwtService(sha256SecretKey, 0, refreshExpirationInMs);
        // When
        String errMsg = assertThrows(IllegalArgumentException.class, () -> jwtService.generateTokenByUserEmail("myemail@gmail.com")).getMessage();
        // Then
        assertEquals("expirationInMs cannot be less than or equal to zero", errMsg);
    }

    @Test
    void generateRefreshTokenByUserEmail_NotNull_Test() {
        // Given
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        // When
        String token = jwtService.generateRefreshTokenByUserEmail("myemail@gmail.com");
        // Then
        assertNotNull(token);
    }

    @Test
    void generateRefreshTokenByUserEmail_Null_Test() {
        // Given
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        // When
        String errMsg = assertThrows(IllegalArgumentException.class, () -> jwtService.generateRefreshTokenByUserEmail(null)).getMessage();
        // Then
        assertEquals("email is empty", errMsg);
    }

    @Test
    void generateRefreshTokenByUserEmail_Expiration_Zero_Test() {
        // Given
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, 0);
        // When
        String errMsg = assertThrows(IllegalArgumentException.class, () -> jwtService.generateRefreshTokenByUserEmail("myemail@gmail.com")).getMessage();
        // Then
        assertEquals("expirationInMs cannot be less than or equal to zero", errMsg);
    }

    @Test
    void doesTokenBelongToUser_Belongs_Test() {
        // Given
        String userEmail = "myemail@gmail.com";
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        String token = jwtService.generateTokenByUserEmail(userEmail);
        // Mock the calls
        UserDetails userDetailsMock = mock(UserDetails.class);
        when(userDetailsMock.getUsername()).thenReturn(userEmail);
        // When
        boolean doesTokenBelongToUser = jwtService.doesTokenBelongToUser(token, userDetailsMock);
        // Then
        assertTrue(doesTokenBelongToUser);
    }

    @Test
    void doesTokenBelongToUser_Does_Not_Belong_Test() {
        // Given
        String userEmail = "myemail@gmail.com";
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        String token = jwtService.generateTokenByUserEmail(userEmail);
        // Mock the calls
        UserDetails userDetailsMock = mock(UserDetails.class);
        when(userDetailsMock.getUsername()).thenReturn("different@gmail.com");
        // When
        boolean doesTokenBelongToUser = jwtService.doesTokenBelongToUser(token, userDetailsMock);
        // Then
        assertFalse(doesTokenBelongToUser);
    }

    @Test
    void doesTokenBelongToUser_Null_Test() {
        // Given
        String userEmail = null;
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        // Mock the calls
        UserDetails userDetailsMock = mock(UserDetails.class);
        // When
        assertThrows(IllegalArgumentException.class, () -> jwtService.doesTokenBelongToUser(userEmail, userDetailsMock));
    }

    @Test
    void extractUserEmailFromToken_Test() {
        // Given
        String userEmail = "myemail@gmail.com";
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        String token = jwtService.generateTokenByUserEmail(userEmail);
        // When
        String extractedUserEmail = jwtService.extractUserEmailFromToken(token);
        // Then
        assertEquals(userEmail, extractedUserEmail);
    }

    @Test
    void extractUserEmailFromToken_Null_Test() {
        // Given
        String token = null;
        JwtService jwtService = new JwtService(sha256SecretKey, jwtExpirationInMs, refreshExpirationInMs);
        // When
        String errMsg = assertThrows(IllegalArgumentException.class, () -> jwtService.extractUserEmailFromToken(token)).getMessage();
        // Then
        assertEquals("token is null", errMsg);
    }
}