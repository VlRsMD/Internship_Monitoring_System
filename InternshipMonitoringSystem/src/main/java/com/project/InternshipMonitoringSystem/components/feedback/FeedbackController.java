package com.project.InternshipMonitoringSystem.components.feedback;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getFeedbacks() {
        return feedbackService.getFeedbacks();
    }

    @PostMapping(path = "candidate/{candidateId}/mentor/{mentorId}")
    public void addFeedback(
            @RequestBody Feedback feedback,
            @PathVariable("candidateId") Long candidateId,
            @PathVariable("mentorId") Long mentorId
    ) {
        feedbackService.addFeedback(feedback, candidateId, mentorId);
    }

    @DeleteMapping(path = "{feedbackId}")
    public void deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }

    @PatchMapping(path = "{feedbackId}")
    public void changeFeedbackText(
            @PathVariable("feedbackId") Long feedbackId,
            @RequestParam(required = false) String feedbackText) {
        feedbackService.changeFeedbackText(feedbackId, feedbackText);
    }
}
