package com.example.springsecuritydemo.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import static com.example.springsecuritydemo.model.Permission.DEVELOPERS_READ;
import static com.example.springsecuritydemo.model.Permission.DEVELOPERS_WRITE;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

/**
 * Роль - это контейнер, который имеет набор полномочий (permissions)
 * Действия в системе могут требовать наличия определённых полномочий у пользователя
 */
public enum Role {
    USER(of(DEVELOPERS_READ).collect(toSet())),
    ADMIN(of(DEVELOPERS_READ, DEVELOPERS_WRITE).collect(toSet()));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(toSet());
    }
}