package com.project.InternshipMonitoringSystem.components.mentor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public List<Mentor> getMentors() {
        return mentorRepository.findAll();
    }

    public void addNewMentor(Mentor mentor) {
        System.out.println(mentor);
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
    public void updateMentor(Long mentorId, String name) {
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalStateException("Mentor with ID " + mentorId + " does not exist."));
        if (name != null &&
                name.length() > 0 &&
                !Objects.equals(mentor.getName(), name)) {
            mentor.setName(name);
        }
    }
}
