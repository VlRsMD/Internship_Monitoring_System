package com.project.InternshipMonitoringSystem.components.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mentor")
public class MentorController {
    private final MentorService mentorService;

    @Autowired
    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    @GetMapping
    public List<Mentor> getMentors() {
        return mentorService.getMentors();
    }

    @PostMapping
    public void registerNewMentor(@RequestBody Mentor mentor) {
        mentorService.addNewMentor(mentor);
    }

    @DeleteMapping(path = "{mentorId}")
    public void deleteMentor(@PathVariable("mentorId") Long mentorId) {
        mentorService.deleteMentor(mentorId);
    }

    @PutMapping(path = "{mentorId}")
    public void updateMentor(
            @PathVariable("mentorId") Long mentorId,
            @RequestParam(required = false) String name) {
        mentorService.updateMentor(mentorId, name);
    }
}
