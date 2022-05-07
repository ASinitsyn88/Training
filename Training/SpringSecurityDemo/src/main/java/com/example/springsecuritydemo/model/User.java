package com.example.springsecuritydemo.model;

import lombok.Data;
import javax.persistence.*;

/**
 * Сущность содержит информацию о пользователе,
 * которая может быть использована, в том числе, для авторизации
 */
@Data
@Entity
@Table(schema = "springsecuritycourse", name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}