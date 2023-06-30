package com.project.InternshipMonitoringSystem.components.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getFeedbacks() {
        return feedbackRepository.findAll();
    }

    public void addNewFeedback(Feedback feedback) {
        System.out.println(feedback);
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
    public void updateFeedback(Long feedbackId, String feedbackText) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalStateException("Feedback with ID " + feedbackId + " does not exist."));
        if (feedbackText != null &&
                feedbackText.length() > 0 &&
                !Objects.equals(feedback.getFeedbackText(), feedbackText)) {
            feedback.setFeedbackText(feedbackText);
        }
    }
}
