package com.project.InternshipMonitoringSystem.components.project;

import com.project.InternshipMonitoringSystem.components.question.Question;
import com.project.InternshipMonitoringSystem.components.question.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final QuestionRepository questionRepository;

    public ProjectService(ProjectRepository projectRepository, QuestionRepository questionRepository) {
        this.projectRepository = projectRepository;
        this.questionRepository = questionRepository;
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public void addProject(Project project, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalStateException("Question with ID " + questionId + " does not exist."));
        project.setQuestion(question);
        projectRepository.save(project);
    }

    public void deleteProject(Long projectId) {
        boolean exists = projectRepository.existsById(projectId);
        if (!exists) {
            throw new IllegalStateException("Project with ID " + projectId + " does not exist.");
        }
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public void changeFunctionalRequirements(Long projectId, String functionalRequirements) {
        if (functionalRequirements != null && functionalRequirements.length() > 0) {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new IllegalStateException("Project with ID " + projectId + " does not exist."));
            if(!Objects.equals(project.getFunctionalRequirements(), functionalRequirements)) {
                project.setFunctionalRequirements(functionalRequirements);
            }
        }
    }
}
