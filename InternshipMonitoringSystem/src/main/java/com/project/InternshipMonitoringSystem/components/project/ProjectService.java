package com.project.InternshipMonitoringSystem.components.project;

import com.project.InternshipMonitoringSystem.components.candidate.CandidateController;
import com.project.InternshipMonitoringSystem.components.question.Question;
import com.project.InternshipMonitoringSystem.components.question.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;

    public ProjectService(ProjectRepository projectRepository,
                          QuestionRepository questionRepository) {
        this.projectRepository = projectRepository;
        this.questionRepository = questionRepository;
    }

    public List<ProjectDTO> getProjects() {
        List<Project> projectsList = projectRepository.findAll();
        return projectsList.stream().map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO fromEntityToDTO(Project project) {
        return new ProjectDTO(project.getId(),
                project.getName(),
                project.getStartDate(),
                project.getEndDate(),
                project.getFunctionalRequirements());
    }

    public void addProject(Project project, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> {
                    logger.error("Question with ID " + questionId + " does not exist. Unable to create a new project.");
                    throw new IllegalStateException("Question with ID " + questionId + " does not exist. Unable to create a new project.");
                });
        project.setQuestion(question);
        projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            logger.error("Project with ID " + projectId + " does not exist. Unable to delete a nonexistent project.");
            throw new IllegalStateException("Project with ID " + projectId + " does not exist. Unable to delete a nonexistent project.");
        }
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public void changeFunctionalRequirements(Long projectId, String functionalRequirements) {
        if (functionalRequirements == null || functionalRequirements.length() == 0) {
            logger.error("Invalid functional requirements. Functional requirements cannot be empty.");
            return;
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> {
                    logger.error("Project with ID " + projectId + " does not exist. Unable to change functional requirements of a nonexistent project.");
                    throw new IllegalStateException("Project with ID " + projectId + " does not exist. Unable to change functional requirements of a nonexistent project.");
                });
        if(!Objects.equals(project.getFunctionalRequirements(), functionalRequirements)) {
            project.setFunctionalRequirements(functionalRequirements);
            logger.info("New functional requirements have been assigned to the project with ID " + projectId + ".");
        }
    }
}
