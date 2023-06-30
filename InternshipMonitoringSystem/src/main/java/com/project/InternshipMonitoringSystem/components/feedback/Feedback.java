package com.project.InternshipMonitoringSystem.components.feedback;

import jakarta.persistence.*;

@Entity
@Table
public class Feedback {
    @Id
    @SequenceGenerator(
            name = "feedback_sequence",
            sequenceName = "feedback_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "feedback_sequence"
    )
    private Long id;
    private String feedbackText;
    private Long candidateID;
    private Long mentorID;

    public Feedback() {
    }

    public Feedback(String feedbackText, Long candidateID, Long mentorID) {
        this.feedbackText = feedbackText;
        this.candidateID = candidateID;
        this.mentorID = mentorID;
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

    public Long getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(Long candidateID) {
        this.candidateID = candidateID;
    }

    public Long getMentorID() {
        return mentorID;
    }

    public void setMentorID(Long mentorID) {
        this.mentorID = mentorID;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", feedbackText='" + feedbackText + '\'' +
                ", candidateID=" + candidateID +
                ", mentorID=" + mentorID +
                '}';
    }
}
