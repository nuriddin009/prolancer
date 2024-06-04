package com.prolancer.FreelanceBazar.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
    ADMIN("admin"),
    MODERATOR("moderator:delete");

    private final String role;


}
