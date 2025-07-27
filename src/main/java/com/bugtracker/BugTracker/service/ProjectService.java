package com.bugtracker.BugTracker.service;

import com.bugtracker.BugTracker.entity.Bug;
import com.bugtracker.BugTracker.entity.Project;
import com.bugtracker.BugTracker.repository.BugRepository;
import com.bugtracker.BugTracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    public Project createProject(Project project) {
        project.setCreatedAt(LocalDateTime.now());
        if (project.getMemberIds() == null) {
            project.setMemberIds(new ArrayList<>());
        }
        return projectRepository.save(project);
    }

    public Project getProjectById(String projectId) throws ProjectNotFoundException {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Project not found with id: " + projectId));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getProjectsByOwner(String ownerId) {
        return projectRepository.findByOwnerId(ownerId);
    }

    public List<Project> getProjectsByMember(String memberId) {
        return projectRepository.findByMemberIdsContaining(memberId);
    }

    public Project updateProject(String projectId, Project updateData) throws ProjectNotFoundException {
        Project existingProject = getProjectById(projectId);

        existingProject.setName(updateData.getName());
        existingProject.setDescription(updateData.getDescription());

        return projectRepository.save(existingProject);
    }

    public Project addMemberToProject(String projectId, String memberId) throws ProjectNotFoundException {
        Project project = getProjectById(projectId);

        if (!project.getMemberIds().contains(memberId)) {
            project.getMemberIds().add(memberId);
            return projectRepository.save(project);
        }
        return project;
    }

    public Project removeMemberFromProject(String projectId, String memberId) throws ProjectNotFoundException {
        Project project = getProjectById(projectId);

        project.getMemberIds().remove(memberId);
        return projectRepository.save(project);
    }

    public void deleteProject(String projectId) throws ProjectNotFoundException {
        if (!projectRepository.existsById(projectId)) {
            throw new ProjectNotFoundException("Cannot delete project. Project not found with id: " + projectId);
        }
        projectRepository.deleteById(projectId);
    }
}
