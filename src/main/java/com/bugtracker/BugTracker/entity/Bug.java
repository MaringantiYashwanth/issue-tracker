package com.bugtracker.BugTracker.entity;

import com.bugtracker.BugTracker.enums.BugStatus;
import com.bugtracker.BugTracker.enums.Priority;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection="bugs")
@Data
public class Bug {
    @Id
    private String id;
    private String title;
    private String description;
    private BugStatus status;
    private Priority priority;
    private String assigneeId;
    private String reporterId;
    private String projectId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> tags;
    private List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
