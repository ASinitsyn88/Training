package com.example.training.token;

import com.example.training.user.Role;
import com.example.training.user.User;
import com.example.training.user.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.List;
import java.util.Optional;
import static com.example.training.token.TokenType.BEARER;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // To be able to use @BeforeAll and @AfterAll in non-static methods
class TokenRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;

    private User user;

    @BeforeAll
    void setUp() {
        Token activeToken = new Token(null, "11111", BEARER, false, false, null);
        Token expiredToken1 = new Token(null, "22222", BEARER, true, true, null);
        Token expiredToken2 = new Token(null, "33333", BEARER, true, true, null);
        User user = new User(null, "Test", "Testov", "test@gmail.com", "123", Role.USER, asList(activeToken, expiredToken1, expiredToken2));

        activeToken.setUser(user);
        expiredToken1.setUser(user);
        expiredToken2.setUser(user);

        this.user = userRepository.save(user);
        tokenRepository.saveAll(asList(activeToken, expiredToken1, expiredToken2));
    }

    @AfterAll
    void tearDown() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAllValidTokenByUser_Found_Test() {
        // Given
        Integer userId = user.getId();
        // When
        List<Token> tokenList = tokenRepository.findAllValidTokenByUser(userId);
        // Then
        assertNotNull(tokenList);
        assertEquals(1, tokenList.size());
        assertEquals("11111", tokenList.get(0).getToken());
        assertEquals(BEARER, tokenList.get(0).getTokenType());
        assertFalse(tokenList.get(0).revoked);
        assertFalse(tokenList.get(0).expired);
    }

    @Test
    void findAllValidTokenByUser_Not_Found_Test() {
        // Given
        Integer userId = -1;
        // When
        List<Token> tokenList = tokenRepository.findAllValidTokenByUser(userId);
        // Then
        assertNotNull(tokenList);
        assertTrue(tokenList.isEmpty());
    }

    @Test
    void findByToken_Found_Test() {
        // Given
        String token = "11111";
        // When
        Optional<Token> dbToken = tokenRepository.findByToken(token);
        // Then
        assertTrue(dbToken.isPresent());
        assertEquals(token, dbToken.get().getToken());
        assertEquals(BEARER, dbToken.get().getTokenType());
        assertFalse(dbToken.get().revoked);
        assertFalse(dbToken.get().expired);
    }

    @Test
    void findByToken_Not_Found_Test() {
        // Given
        String token = "test_token_value";
        // When
        Optional<Token> dbToken = tokenRepository.findByToken(token);
        // Then
        assertFalse(dbToken.isPresent());
    }
}