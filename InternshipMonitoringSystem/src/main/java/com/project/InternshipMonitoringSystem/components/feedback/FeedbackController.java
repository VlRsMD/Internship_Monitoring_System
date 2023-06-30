package com.project.InternshipMonitoringSystem.components.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getCandidates() {
        return feedbackService.getFeedbacks();
    }

    @PostMapping
    public void registerNewFeedback(@RequestBody Feedback feedback) {
        feedbackService.addNewFeedback(feedback);
    }

    @DeleteMapping(path = "{feedbackId}")
    public void deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
    }

    @PutMapping(path = "{feedbackId}")
    public void updateFeedback(
            @PathVariable("feedbackId") Long feedbackId,
            @RequestParam(required = false) String feedbackText) {
        feedbackService.updateFeedback(feedbackId, feedbackText);
    }
}
