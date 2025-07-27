package com.bugtracker.BugTracker.repository;

import com.bugtracker.BugTracker.entity.Bug;
import com.bugtracker.BugTracker.enums.BugStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends MongoRepository<Bug, String> {
    List<Bug> findByProjectId(String projectId);
    List<Bug> findByAssigneeId(String assigneeId);
    List<Bug> findByStatus(BugStatus status);
    List<Bug> findByProjectIdAndStatus(String projectId, BugStatus status);
}