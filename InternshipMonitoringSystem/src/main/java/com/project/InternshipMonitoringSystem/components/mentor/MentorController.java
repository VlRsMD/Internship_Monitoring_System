package com.project.InternshipMonitoringSystem.components.mentor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mentor")
public class MentorController {
    Logger logger = LoggerFactory.getLogger(MentorController.class);

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<MentorDTO> getMentors() {
        logger.trace("'getMentors' method accessed");
        return mentorService.getMentors();
    }

    @PostMapping(path = "project/{projectId}")
    public void addMentor(
            @RequestBody Mentor mentor,
            @PathVariable("projectId") Long projectId) {
        logger.trace("'addMentor' method accessed");
        mentorService.addMentor(mentor, projectId);
    }

    @DeleteMapping(path = "{mentorId}")
    public void deleteMentor(@PathVariable("mentorId") Long mentorId) {
        logger.trace("'deleteMentor' method accessed");
        mentorService.deleteMentor(mentorId);
    }

    @PatchMapping(path = "{mentorId}/project/{projectId}")
    public void addProjectToSupervise(
            @PathVariable("mentorId") Long mentorId,
            @PathVariable("projectId") Long projectId) {
        logger.trace("'addProjectToSupervise' method accessed");
        mentorService.addProjectToSupervise(mentorId, projectId);
    }
}
