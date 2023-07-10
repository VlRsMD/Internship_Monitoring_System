package com.project.InternshipMonitoringSystem.components.feedback;

import com.project.InternshipMonitoringSystem.components.candidate.Candidate;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateRepository;
import com.project.InternshipMonitoringSystem.components.mentor.Mentor;
import com.project.InternshipMonitoringSystem.components.mentor.MentorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final CandidateRepository candidateRepository;
    private final MentorRepository mentorRepository;

    public FeedbackService(FeedbackRepository feedbackRepository, CandidateRepository candidateRepository, MentorRepository mentorRepository) {
        this.feedbackRepository = feedbackRepository;
        this.candidateRepository = candidateRepository;
        this.mentorRepository = mentorRepository;
    }

    public List<Feedback> getFeedbacks() {
        return feedbackRepository.findAll();
    }

    public void addFeedback(Feedback feedback, Long candidateId, Long mentorId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalStateException("Candidate with ID " + candidateId + " does not exist."));
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> new IllegalStateException("Mentor with ID " + mentorId + " does not exist."));
        feedback.setCandidate(candidate);
        feedback.setMentor(mentor);
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId) {
        boolean exists = feedbackRepository.existsById(feedbackId);
        if (!exists) {
            throw new IllegalStateException("Feedback with ID " + feedbackId + " does not exist.");
        }
        feedbackRepository.deleteById(feedbackId);
    }

    @Transactional
    public void changeFeedbackText(Long feedbackId, String feedbackText) {
        if (feedbackText != null && feedbackText.length() > 0) {
            Feedback feedback = feedbackRepository.findById(feedbackId)
                    .orElseThrow(() -> new IllegalStateException("Feedback with ID " + feedbackId + " does not exist."));
            if(!Objects.equals(feedback.getFeedbackText(), feedbackText)) {
                feedback.setFeedbackText(feedbackText);
            }
        }
    }
}
