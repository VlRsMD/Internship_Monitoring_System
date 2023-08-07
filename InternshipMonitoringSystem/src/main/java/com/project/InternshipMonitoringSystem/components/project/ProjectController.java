package com.project.InternshipMonitoringSystem.components.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
public class ProjectController {
    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getProjects() {
        logger.info("Request to fetch projects in the system.");
        return projectService.getProjects();
    }

    @PostMapping("question/{questionId}")
    public void addProject(
            @RequestBody Project project,
            @PathVariable("questionId") Long questionId) {
        logger.info("Request to add a new project into the system.");
        projectService.addProject(project, questionId);
    }

    @DeleteMapping("{projectId}")
    public void deleteProject(@PathVariable("projectId") Long projectId) {
        logger.info("Request to delete a particular project from the system.");
        projectService.deleteProject(projectId);
    }

    @PatchMapping("{projectId}")
    public void changeFunctionalRequirements(
            @PathVariable("projectId") Long projectId,
            @RequestParam(required = false) String functionalRequirements) {
        logger.info("Request to change the functional requirements of a particular project.");
        projectService.changeFunctionalRequirements(projectId, functionalRequirements);
    }
}

