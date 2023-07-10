package com.project.InternshipMonitoringSystem.components.candidate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.InternshipMonitoringSystem.components.feedback.Feedback;
import com.project.InternshipMonitoringSystem.components.mark.Mark;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Candidate {
    @Id
    @SequenceGenerator(
            name = "candidate_sequence",
            sequenceName = "candidate_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "candidate_sequence"
    )

    private Long id;
    private String name;
    private String emailAddress;
    private String comment;
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    private Set<Feedback> feedbacks = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "candidate")
    private Set<Mark> marks = new HashSet<>();

    public Candidate() {
    }

    public Candidate(String name, String emailAddress, String comment, String status) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.comment = comment;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", comment='" + comment + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

