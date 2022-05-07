package com.example.springsecuritydemo.model;

/**
 * Полномочие - это право на выполнение какого-либо действия
 * Действия в системе могут требовать наличия определённых полномочий у пользователя
 */
public enum Permission {
    DEVELOPERS_READ("developers:read"),
    DEVELOPERS_WRITE("developers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}