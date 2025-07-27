package com.bugtracker.BugTracker.dto;

import com.bugtracker.BugTracker.enums.Role;

import lombok.Data;

@Data
public class UserRegistrationRequest {


    private String username;

    private String email;

    private String password;

    private Role role;
}
