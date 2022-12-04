package com.fitplanner.common;

import lombok.Getter;

@Getter
public enum RoleLevel {

    ROLE_SUPERADMIN("1"),
    ROLE_SUBADMIN("2"),
    ROLE_MEMBERADMIN("3"),
    ROLE_VIPMEMBER("4"),
    ROLE_MEMBER("5");

    private String roleLevel;

    RoleLevel(String roleLevel) {
        this.roleLevel = roleLevel;
    }

}
