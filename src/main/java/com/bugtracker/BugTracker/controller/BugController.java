package com.bugtracker.BugTracker.controller;

import com.bugtracker.BugTracker.dto.StatusUpdateRequest;
import com.bugtracker.BugTracker.entity.Bug;
import com.bugtracker.BugTracker.enums.BugStatus;
import com.bugtracker.BugTracker.service.BugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/bugs")
@CrossOrigin(origins="http://localhost:5173")
public class BugController {

    private final BugService bugService;
    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @GetMapping  // This will handle GET /api/bugs
    public ResponseEntity<List<Bug>> getAllBugs() {
        List<Bug> bugs = bugService.getAllBugs();  // You'll need this method in your service
        return ResponseEntity.ok(bugs);
    }

    @PostMapping
    public ResponseEntity<Bug> createBug(@RequestBody Bug bug) {
        Bug createdBug = bugService.createBug(bug);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBug);
    }

    @GetMapping("project/projectId")
    public ResponseEntity<List<Bug>> getBugsByProject(@PathVariable String projectId) {
        List<Bug> bugs = bugService.getBugsByProject(projectId);
        return ResponseEntity.ok(bugs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bug> getBugById(@PathVariable String id)
    {
        Bug updatedBug = bugService.getBugById(id);
        return ResponseEntity.ok(updatedBug);
    }
    @PatchMapping("/{bugId}/status")
    public ResponseEntity<Bug> updateBugStatus(@PathVariable String bugId, @RequestBody StatusUpdateRequest request) {
        Bug updatedBug = bugService.updatedBugStatus(bugId, request.getStatus());
        return ResponseEntity.ok(updatedBug);
    }

    @GetMapping("/assignee/{assigneeId}")
    public ResponseEntity<List<Bug>> getBugsByAssignee(@PathVariable String assigneeId) {
        List<Bug> bugs = bugService.findByAssigneeId(assigneeId);
        return ResponseEntity.ok(bugs);
    }

    // Get bugs by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Bug>> findByStatus(@PathVariable BugStatus status) {
        List<Bug> bugs = bugService.findByStatus(status);
        return ResponseEntity.ok(bugs);
    }

    // Get bugs by project and status
    @GetMapping("/project/{projectId}/status/{status}")
    public ResponseEntity<List<Bug>> getBugsByProjectAndStatus(
            @PathVariable String projectId,
            @PathVariable BugStatus status) {
        List<Bug> bugs = bugService.findByProjectIdAndStatus(projectId, status);
        return ResponseEntity.ok(bugs);
    }

    // Convenience endpoints for common status queries
    @GetMapping("/open")
    public ResponseEntity<List<Bug>> getOpenBugs() {
        List<Bug> bugs = bugService.getOpenBugs();
        return ResponseEntity.ok(bugs);
    }

    @GetMapping("/in-progress")
    public ResponseEntity<List<Bug>> getInProgressBugs() {
        List<Bug> bugs = bugService.getInProgressBugs();
        return ResponseEntity.ok(bugs);
    }

    @GetMapping("/project/{projectId}/open")
    public ResponseEntity<List<Bug>> getOpenBugsInProject(@PathVariable String projectId) {
        List<Bug> bugs = bugService.getOpenBugsInProject(projectId);
        return ResponseEntity.ok(bugs);
    }
}

