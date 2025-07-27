package com.bugtracker.BugTracker.service;

import com.bugtracker.BugTracker.entity.Bug;
import com.bugtracker.BugTracker.entity.Comment;
import com.bugtracker.BugTracker.enums.BugStatus;
import com.bugtracker.BugTracker.repository.BugRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDateTime;

@Service
@Transactional
public class BugService {
    private BugRepository bugRepository;
    public BugService(BugRepository bugRepository) {
        this.bugRepository = bugRepository;
    }

    public Bug createBug(Bug bug) {
        bug.setCreatedAt(LocalDateTime.now());
        bug.setUpdatedAt(LocalDateTime.now());
        bug.setStatus(BugStatus.OPEN);
        return bugRepository.save(bug);
    }

    public Bug updatedBugStatus(String bugId, BugStatus status) {
        Bug bug = bugRepository.findById(bugId)
                .orElseThrow(() -> new BugNotFoundException("Bug with id " + bugId + " not found"));
        bug.setStatus(status);
        return bugRepository.save(bug);
    }

    public List<Bug> getBugsByProject(String projectId) {
        return bugRepository.findByProjectId(projectId);
    }
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }
    public Bug updatedBugStatus(String bugId, BugStatus status, String statusComment, String updatedBy) {
        Bug bug = bugRepository.findById(bugId)
                .orElseThrow(() -> new BugNotFoundException("Bug not found with id: " + bugId));

        // Update status
        bug.setStatus(status);
        bug.setUpdatedAt(LocalDateTime.now());

        // Add status change comment if provided
        if (statusComment != null && !statusComment.trim().isEmpty()) {
            Comment comment = new Comment(
                    "Status changed to " + status.getDisplayName() + ": " + statusComment,
                    updatedBy,
                    updatedBy // You might want to fetch actual user name
            );
            bug.addComment(comment);
        }

        return bugRepository.save(bug);
    }

    public List<Bug> findByAssigneeId(String assigneeId) {
        if (assigneeId == null || assigneeId.trim().isEmpty()) {
            throw new IllegalArgumentException("Assignee ID cannot be null or empty");
        }
        return bugRepository.findByAssigneeId(assigneeId);
    }
    public List<Bug> findByStatus(BugStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return bugRepository.findByStatus(status);
    }

    public List<Bug> findByProjectIdAndStatus(String projectId, BugStatus status) {
        if (projectId == null || projectId.trim().isEmpty()) {
            throw new IllegalArgumentException("Project ID cannot be null or empty");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        return bugRepository.findByProjectIdAndStatus(projectId, status);
    }

    public List<Bug> getOpenBugs() {
        return findByStatus(BugStatus.OPEN);
    }

    public List<Bug> getInProgressBugs() {
        return findByStatus(BugStatus.IN_PROGRESS);
    }

    public List<Bug> getOpenBugsInProject(String projectId) {
        return findByProjectIdAndStatus(projectId, BugStatus.OPEN);
    }
}

