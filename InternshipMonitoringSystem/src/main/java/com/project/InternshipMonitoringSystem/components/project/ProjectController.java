package com.project.InternshipMonitoringSystem.components.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/project")
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getProjects() {
        logger.trace("'getProjects' method accessed");
        return projectService.getProjects();
    }

    @PostMapping(path = "question/{questionId}")
    public void addProject(
            @RequestBody Project project,
            @PathVariable("questionId") Long questionId) {
        logger.trace("'addProject' method accessed");
        projectService.addProject(project, questionId);
    }

    @DeleteMapping(path = "{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        logger.trace("'deleteProject' method accessed");
        projectService.deleteProject(projectId);
    }

    @PatchMapping(path = "{projectId}")
    public void changeFunctionalRequirements(
            @PathVariable("projectId") Long projectId,
            @RequestParam(required = false) String functionalRequirements) {
        logger.trace("'changeFunctionalRequirements' method accessed");
        projectService.changeFunctionalRequirements(projectId, functionalRequirements);
    }
}

