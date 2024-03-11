package com.example.training.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Permission is the right to perform particular action
 * Different roles might have the same set of permissions
 * Actions in the system may require the user to have certain permissions
 */
@RequiredArgsConstructor
public enum Permission {
    CHECK_STATUS("check_status"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permission;
}