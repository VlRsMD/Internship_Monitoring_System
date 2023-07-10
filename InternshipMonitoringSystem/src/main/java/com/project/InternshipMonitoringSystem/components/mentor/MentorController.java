package com.project.InternshipMonitoringSystem.components.mentor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mentor")
public class MentorController {
    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<Mentor> getMentors() {
        return mentorService.getMentors();
    }

    @PostMapping(path = "project/{projectId}")
    public void addMentor(
            @RequestBody Mentor mentor,
            @PathVariable("projectId") Long projectId
    ) {
        mentorService.addMentor(mentor, projectId);
    }

    @DeleteMapping(path = "{mentorId}")
    public void deleteMentor(@PathVariable("mentorId") Long mentorId) {
        mentorService.deleteMentor(mentorId);
    }

    @PatchMapping(path = "{mentorId}/project/{projectId}")
    public void addProjectToSupervise(
            @PathVariable("mentorId") Long mentorId,
            @PathVariable("projectId") Long projectId
    ) {
        mentorService.addProjectToSupervise(mentorId, projectId);
    }
}
