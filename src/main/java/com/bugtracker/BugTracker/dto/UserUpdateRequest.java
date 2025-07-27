package com.bugtracker.BugTracker.dto;

import com.bugtracker.BugTracker.enums.Role;

import lombok.Data;

@Data
public class UserUpdateRequest {

    private String username;
    private String email;

    private Role role;
}
