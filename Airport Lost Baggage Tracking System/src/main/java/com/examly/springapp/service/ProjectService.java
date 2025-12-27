package com.examly.springapp.service;

import com.examly.springapp.model.Project;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    private final Map<Long, Project> projects = new LinkedHashMap<>();
    private long id = 1;

    public Project addProject(Project project) {
        project.setProjectId(id);
        projects.put(id, project);
        id++;
        return project;
    }

    public List<Project> getAllProjects() {
        return new ArrayList<>(projects.values());
    }

    public Project getProjectById(Long id) {
        return projects.get(id);
    }

    public Project updateProject(Long id, Project project) {
        Project existing = projects.get(id);
        if (existing != null) {
            existing.setProjectName(project.getProjectName());
            existing.setDescription(project.getDescription());
            existing.setStatus(project.getStatus());
        }
        return existing;
    }

    public List<Project> getProjectsByStatus(String status) {
        List<Project> result = new ArrayList<>();
        for (Project project : projects.values()) {
            if (project.getStatus() != null && project.getStatus().equals(status)) {
                result.add(project);
            }
        }
        return result;
    }
}
