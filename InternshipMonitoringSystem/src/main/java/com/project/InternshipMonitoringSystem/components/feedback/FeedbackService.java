package com.project.InternshipMonitoringSystem.components.feedback;

import com.project.InternshipMonitoringSystem.components.candidate.Candidate;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateController;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateRepository;
import com.project.InternshipMonitoringSystem.components.mentor.Mentor;
import com.project.InternshipMonitoringSystem.components.mentor.MentorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final FeedbackRepository feedbackRepository;
    private final CandidateRepository candidateRepository;
    private final MentorRepository mentorRepository;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           CandidateRepository candidateRepository,
                           MentorRepository mentorRepository) {
        this.feedbackRepository = feedbackRepository;
        this.candidateRepository = candidateRepository;
        this.mentorRepository = mentorRepository;
    }

    public List<FeedbackDTO> getFeedbacks() {
        List<Feedback> feedbacksList = feedbackRepository.findAll();
        return feedbacksList.stream().map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public FeedbackDTO fromEntityToDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getId(),
                feedback.getFeedbackText(),
                feedback.getCandidate().getId(),
                feedback.getMentor().getId());
    }

    public void addFeedback(Feedback feedback, Long candidateId, Long mentorId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    logger.error("Candidate with ID " + candidateId + " does not exist. Unable to create a new feedback.");
                    throw new IllegalStateException("Candidate with ID " + candidateId + " does not exist. Unable to create a new feedback.");
                });
        Mentor mentor = mentorRepository.findById(mentorId)
                .orElseThrow(() -> {
                    logger.error("Mentor with ID " + mentorId + " does not exist. Unable to create a new feedback.");
                    throw new IllegalStateException("Mentor with ID " + mentorId + " does not exist. Unable to create a new feedback.");
                });
        feedback.setCandidate(candidate);
        feedback.setMentor(mentor);
        feedbackRepository.save(feedback);
    }

    public void deleteFeedback(Long feedbackId) {
        boolean exists = feedbackRepository.existsById(feedbackId);
        if (!exists) {
            logger.error("Feedback with ID " + feedbackId + " does not exist. Unable to delete a nonexistent feedback.");
            throw new IllegalStateException("Feedback with ID " + feedbackId + " does not exist. Unable to delete a nonexistent feedback.");
        }
        feedbackRepository.deleteById(feedbackId);
    }

    @Transactional
    public void changeFeedbackText(Long feedbackId, String feedbackText) {
        if (feedbackText == null || feedbackText.length() == 0) {
                logger.error("Invalid feedback text. Feedback text cannot be empty.");
                return;
            }
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> {
                    logger.error("Feedback with ID " + feedbackId + " does not exist. Unable to change text of a nonexistent feedback.");
                    throw new IllegalStateException("Feedback with ID " + feedbackId + " does not exist. Unable to change text of a nonexistent feedback.");
                });
        if(!Objects.equals(feedback.getFeedbackText(), feedbackText)) {
            feedback.setFeedbackText(feedbackText);
            logger.info("New feedback text has been assigned to the feedback with ID " + feedbackId + ".");
        }
    }
}
