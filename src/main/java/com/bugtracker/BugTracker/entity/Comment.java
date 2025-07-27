package com.bugtracker.BugTracker.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;
@Document(collection = "comments")
@Data
public class Comment {
    @Id
    private String id;
    private String content;
    private String authorId;
    private String authorName; // For easy display
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Comment() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Comment(String content, String authorId, String authorName) {
        this.content = content;
        this.authorId = authorId;
        this.authorName = authorName;
    }
}
