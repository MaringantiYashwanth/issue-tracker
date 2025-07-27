package com.bugtracker.BugTracker.controller;

import com.bugtracker.BugTracker.dto.MemberRequest;
import com.bugtracker.BugTracker.entity.Project;
import com.bugtracker.BugTracker.service.ProjectNotFoundException;
import com.bugtracker.BugTracker.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject( @RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(@PathVariable String projectId) throws ProjectNotFoundException {
        Project project = projectService.getProjectById(projectId);
        return ResponseEntity.ok(project);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Project>> getProjectsByOwner(@PathVariable String ownerId) {
        List<Project> projects = projectService.getProjectsByOwner(ownerId);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Project>> getProjectsByMember(@PathVariable String memberId) {
        List<Project> projects = projectService.getProjectsByMember(memberId);
        return ResponseEntity.ok(projects);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable String projectId,
            @RequestBody Project project) throws ProjectNotFoundException {
        Project updatedProject = projectService.updateProject(projectId, project);
        return ResponseEntity.ok(updatedProject);
    }

    @PostMapping("/{projectId}/members")
    public ResponseEntity<Project> addMember(
            @PathVariable String projectId,
            @RequestBody MemberRequest request) throws ProjectNotFoundException {
        Project updatedProject = projectService.addMemberToProject(projectId, request.getMemberId());
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}/members/{memberId}")
    public ResponseEntity<Project> removeMember(
            @PathVariable String projectId,
            @PathVariable String memberId) throws ProjectNotFoundException {
        Project updatedProject = projectService.removeMemberFromProject(projectId, memberId);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) throws ProjectNotFoundException {
        projectService.deleteProject(projectId);
        return ResponseEntity.noContent().build();
    }
}
