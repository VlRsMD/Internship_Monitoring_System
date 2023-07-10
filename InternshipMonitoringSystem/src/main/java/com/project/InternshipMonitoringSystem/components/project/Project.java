package com.project.InternshipMonitoringSystem.components.project;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.InternshipMonitoringSystem.components.mentor.Mentor;
import com.project.InternshipMonitoringSystem.components.question.Question;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Project {
    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )

    private Long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private String status;
    private String gitHubLink;
    private String trelloLink;
    private String powerPointPresentation;
    private String functionalRequirements;

    @JsonIgnore
    @ManyToMany(mappedBy = "supervisedProjects")
    private Set<Mentor> mentors = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "technical_questionid", referencedColumnName = "id")
    private Question question;

    public Project() {
    }

    public Project(String name, Date startDate, Date endDate, String status, String gitHubLink, String trelloLink, String powerPointPresentation, String functionalRequirements) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.gitHubLink = gitHubLink;
        this.trelloLink = trelloLink;
        this.powerPointPresentation = powerPointPresentation;
        this.functionalRequirements = functionalRequirements;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }

    public String getTrelloLink() {
        return trelloLink;
    }

    public void setTrelloLink(String trelloLink) {
        this.trelloLink = trelloLink;
    }

    public String getPowerPointPresentation() {
        return powerPointPresentation;
    }

    public void setPowerPointPresentation(String powerPointPresentation) {
        this.powerPointPresentation = powerPointPresentation;
    }

    public String getFunctionalRequirements() {
        return functionalRequirements;
    }

    public void setFunctionalRequirements(String functionalRequirements) {
        this.functionalRequirements = functionalRequirements;
    }

    public Set<Mentor> getMentors() {
        return mentors;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", technicalQuestionID=" + question.getId() +
                ", gitHubLink='" + gitHubLink + '\'' +
                ", trelloLink='" + trelloLink + '\'' +
                ", powerPointPresentation='" + powerPointPresentation + '\'' +
                ", functionalRequirements='" + functionalRequirements + '\'' +
                '}';
    }
}