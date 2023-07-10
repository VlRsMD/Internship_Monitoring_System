package com.project.InternshipMonitoringSystem.components.project;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectService.getProjects();
    }

    @PostMapping(path = "question/{questionId}")
    public void addProject(
            @RequestBody Project project,
            @PathVariable("questionId") Long questionId
    ) {
        projectService.addProject(project, questionId);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        projectService.deleteProject(projectId);
    }

    @PatchMapping(path = "{projectId}")
    public void changeFunctionalRequirements(
            @PathVariable("projectId") Long projectId,
            @RequestParam(required = false) String functionalRequirements) {
        projectService.changeFunctionalRequirements(projectId, functionalRequirements);
    }
}

