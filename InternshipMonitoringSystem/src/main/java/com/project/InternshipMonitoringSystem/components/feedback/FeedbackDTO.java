package com.project.InternshipMonitoringSystem.components.feedback;

public class FeedbackDTO {
    private Long id;
    private String feedbackText;
    private Long candidateId;
    private Long mentorId;

    public FeedbackDTO(Long id, String feedbackText, Long candidateId, Long mentorId) {
        this.id = id;
        this.feedbackText = feedbackText;
        this.candidateId = candidateId;
        this.mentorId = mentorId;
    }

    public FeedbackDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }
}
