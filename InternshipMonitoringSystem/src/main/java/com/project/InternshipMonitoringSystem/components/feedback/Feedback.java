package com.project.InternshipMonitoringSystem.components.feedback;

import com.project.InternshipMonitoringSystem.components.candidate.Candidate;
import com.project.InternshipMonitoringSystem.components.mentor.Mentor;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidateid", referencedColumnName = "id")
    private Candidate candidate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mentorid", referencedColumnName = "id")
    private Mentor mentor;

    public Feedback() {
    }

    public Feedback(String feedbackText) {
        this.feedbackText = feedbackText;
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

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", feedbackText='" + feedbackText + '\'' +
                ", candidateID=" + candidate.getId() +
                ", mentorID=" + mentor.getId() +
                '}';
    }
}
