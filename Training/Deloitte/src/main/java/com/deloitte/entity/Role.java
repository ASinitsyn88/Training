package com.deloitte.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import static com.deloitte.entity.Permission.READ;
import static com.deloitte.entity.Permission.WRITE;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

public enum Role {
    USER(of(READ).collect(toSet())),
    ADMIN(of(READ, WRITE).collect(toSet()));

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