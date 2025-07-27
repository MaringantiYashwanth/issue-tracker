package com.bugtracker.BugTracker.enums;

public enum BugStatus {
    OPEN("Open", "Bug has been reported and is awaiting triage"),
    IN_PROGRESS("In Progress", "Bug is currently being worked on"),
    RESOLVED("Resolved", "Bug has been fixed but not yet verified"),
    CLOSED("Closed", "Bug has been resolved and verified"),
    REOPENED("Reopened", "Previously resolved bug has been reopened"),
    WONT_FIX("Won't Fix", "Bug will not be addressed"),
    DUPLICATE("Duplicate", "Bug is a duplicate of another issue"),
    ON_HOLD("On Hold", "Bug work is temporarily paused"),
    INVALID("Invalid", "Bug report is not valid or reproducible");

    private final String displayName;
    private final String description;

    BugStatus(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
