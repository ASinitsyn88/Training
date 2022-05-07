package com.example.springsecuritydemo.security;

import com.example.springsecuritydemo.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import static com.example.springsecuritydemo.model.Status.ACTIVE;

/**
 * Содержит информацию, необходимую для аутентификации пользователя
 */
@Data
public class SecurityUser implements UserDetails {
    // Логин
    private final String username;
    // Пароль
    private final String password;
    // Список авторизаций
    private List<SimpleGrantedAuthority> authorities;
    // Признак блокировки
    private final boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    /**
     * Метод конвертирует объект с общей информацией о пользователе
     * в объект с информацией, которая необходима для авторизации
     * @param user - объект с общей информацией о пользователе
     * @return объект с информацией, которая необходима для авторизации
     */
    public static UserDetails fromUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getStatus().equals(ACTIVE),
                user.getStatus().equals(ACTIVE),
                user.getStatus().equals(ACTIVE),
                user.getStatus().equals(ACTIVE),
                user.getRole().getAuthorities()
        );
    }
}