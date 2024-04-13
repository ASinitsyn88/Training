package com.example.training.token;

import com.example.training.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "token_generator")
    @SequenceGenerator(name = "token_generator", sequenceName = "token_id_seq", allocationSize = 1)
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    // token table will have user_id column in which it will store user identifier
    // orm will transform it to the User class
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}