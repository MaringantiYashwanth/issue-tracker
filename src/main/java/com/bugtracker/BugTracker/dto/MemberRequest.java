package com.bugtracker.BugTracker.dto;


public class MemberRequest {
    private String memberId;

    public MemberRequest() {}

    public MemberRequest(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
