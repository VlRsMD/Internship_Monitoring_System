package com.project.InternshipMonitoringSystem.components.mentor;

import com.project.InternshipMonitoringSystem.components.project.Project;
import com.project.InternshipMonitoringSystem.components.project.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;
    private final ProjectRepository projectRepository;

    public MentorService(MentorRepository mentorRepository,
                         ProjectRepository projectRepository) {
        this.mentorRepository = mentorRepository;
        this.projectRepository = projectRepository;
    }

    public List<MentorDTO> getMentors() {
        List<Mentor> mentorsList = mentorRepository.findAll();
        return mentorsList.stream().map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public MentorDTO fromEntityToDTO(Mentor mentor) {
        List<Long> projectIds = new ArrayList<>();
        List<Project> supervisedProjects = mentor.getSupervisedProjects().stream().toList();
        for (Project supervisedProject : supervisedProjects) {
            projectIds.add(supervisedProject.getId());
        }
        return new MentorDTO(mentor.getId(), mentor.getName(), projectIds);
    }

    public void addMentor(Mentor mentor, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with ID " + projectId + " does not exist."));
        mentor.registerProject(project);
        mentorRepository.save(mentor);
    }

    public void deleteMentor(Long mentorId) {
        boolean exists = mentorRepository.existsById(mentorId);
        if (!exists) {
            throw new IllegalStateException("Mentor with ID " + mentorId + " does not exist.");
        }
        mentorRepository.deleteById(mentorId);
    }

    @Transactional
    public void addProjectToSupervise(Long mentorId, Long projectId) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalStateException("Mentor with ID " + mentorId + " does not exist."));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalStateException("Project with ID " + projectId + " does not exist."));
        mentor.registerProject(project);
    }
}
