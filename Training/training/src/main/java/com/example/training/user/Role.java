package com.example.training.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Set;
import static com.example.training.user.Permission.*;
import static java.util.Set.of;
import static java.util.stream.Collectors.toList;

/**
 * Role is a container that has a set of permissions
 * Actions in the system may require the user to have certain permissions
 */
@RequiredArgsConstructor
public enum Role {
    USER(of(CHECK_STATUS)),
    ADMIN(of(CHECK_STATUS, ADMIN_READ, ADMIN_UPDATE, ADMIN_DELETE, ADMIN_CREATE));

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}