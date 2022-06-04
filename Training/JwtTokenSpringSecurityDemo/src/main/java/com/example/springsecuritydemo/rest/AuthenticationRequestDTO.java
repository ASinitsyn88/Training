package com.example.springsecuritydemo.rest;

import lombok.Data;

/**
 * Структура данных, используемая для аутентификации
 */
@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}