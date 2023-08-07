package com.project.InternshipMonitoringSystem.components.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/feedback")
public class FeedbackController {
    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackDTO> getFeedbacks() {
        logger.info("Request to fetch feedbacks in the system.");
        return feedbackService.getFeedbacks();
    }

    @PostMapping("candidate/{candidateId}/mentor/{mentorId}")
    public void addFeedback(@RequestBody Feedback feedback,
                            @PathVariable("candidateId") Long candidateId,
                            @PathVariable("mentorId") Long mentorId) {
        logger.info("Request to add a new feedback into the system.");
        feedbackService.addFeedback(feedback, candidateId, mentorId);
    }

    @DeleteMapping("{feedbackId}")
    public void deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        logger.info("Request to delete a particular feedback from the system.");
        feedbackService.deleteFeedback(feedbackId);
    }

    @PatchMapping("{feedbackId}")
    public void changeFeedbackText(
            @PathVariable("feedbackId") Long feedbackId,
            @RequestParam(required = false) String feedbackText) {
        logger.info("Request to change the feedback text of a particular feedback.");
        feedbackService.changeFeedbackText(feedbackId, feedbackText);
    }
}
