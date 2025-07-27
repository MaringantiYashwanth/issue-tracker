package com.bugtracker.BugTracker.dto;

import com.bugtracker.BugTracker.enums.BugStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusUpdateRequest {
    private BugStatus status;

    private String comment;

    private String assigneeId;
}
