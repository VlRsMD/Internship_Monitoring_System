package com.project.InternshipMonitoringSystem.components.mentor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mentor")
public class MentorController {
    private static final Logger logger = LoggerFactory.getLogger(MentorController.class);

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<MentorDTO> getMentors() {
        logger.info("Request to fetch mentors in the system.");
        return mentorService.getMentors();
    }

    @PostMapping(path = "project/{projectId}")
    public void addMentor(
            @RequestBody Mentor mentor,
            @PathVariable("projectId") Long projectId) {
        logger.info("Request to add a new mentor into the system.");
        mentorService.addMentor(mentor, projectId);
    }

    @DeleteMapping(path = "{mentorId}")
    public void deleteMentor(@PathVariable("mentorId") Long mentorId) {
        logger.info("Request to delete a particular mentor from the system.");
        mentorService.deleteMentor(mentorId);
    }

    @PatchMapping(path = "{mentorId}/project/{projectId}")
    public void addProjectToSupervise(
            @PathVariable("mentorId") Long mentorId,
            @PathVariable("projectId") Long projectId) {
        logger.info("Request to add a project to supervise to a particular mentor.");
        mentorService.addProjectToSupervise(mentorId, projectId);
    }
}
