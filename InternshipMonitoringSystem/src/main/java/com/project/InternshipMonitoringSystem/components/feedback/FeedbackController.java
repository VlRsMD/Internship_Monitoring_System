package com.project.InternshipMonitoringSystem.components.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/feedback")
public class FeedbackController {
    Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<FeedbackDTO> getFeedbacks() {
        logger.trace("'getFeedbacks' method accessed");
        return feedbackService.getFeedbacks();
    }

    @PostMapping(path = "candidate/{candidateId}/mentor/{mentorId}")
    public void addFeedback(@RequestBody Feedback feedback,
                            @PathVariable("candidateId") Long candidateId,
                            @PathVariable("mentorId") Long mentorId) {
        logger.trace("'addFeedback' method accessed");
        feedbackService.addFeedback(feedback, candidateId, mentorId);
    }

    @DeleteMapping(path = "{feedbackId}")
    public void deleteFeedback(@PathVariable("feedbackId") Long feedbackId) {
        logger.trace("'deleteFeedback' method accessed");
        feedbackService.deleteFeedback(feedbackId);
    }

    @PatchMapping(path = "{feedbackId}")
    public void changeFeedbackText(
            @PathVariable("feedbackId") Long feedbackId,
            @RequestParam(required = false) String feedbackText) {
        logger.trace("'changeFeedbackText' method accessed");
        feedbackService.changeFeedbackText(feedbackId, feedbackText);
    }
}
