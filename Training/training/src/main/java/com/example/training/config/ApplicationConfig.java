package com.example.training.config;

import com.example.training.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;

    /**
     * See UserDetailsService on jwt_mechanism schema
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userEmail -> repository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}