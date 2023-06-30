package com.project.InternshipMonitoringSystem.components.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public void addNewProject(Project mentor) {
        System.out.println(mentor);
        projectRepository.save(mentor);
    }

    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new IllegalStateException("Project with ID " + projectId + " does not exist.");
        }
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public void updateProject(Long projectId, String functionalRequirements) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with ID " + projectId + " does not exist."));
        if (functionalRequirements != null &&
                functionalRequirements.length() > 0 &&
                !Objects.equals(project.getFunctionalRequirements(), functionalRequirements)) {
            project.setFunctionalRequirements(functionalRequirements);
        }
    }
}
