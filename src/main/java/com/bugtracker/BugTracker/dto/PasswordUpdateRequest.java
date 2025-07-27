package com.bugtracker.BugTracker.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequest {

    private String newPassword;

    public PasswordUpdateRequest() {}

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
