package com.project.InternshipMonitoringSystem.components.project;

import jakarta.persistence.*;

import java.util.Date;

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
    private Long technicalQuestionID;
    private String gitHubLink;
    private String trelloLink;
    private String powerPointPresentation;
    private String functionalRequirements;

    public Project() {
    }

    public Project(String name, Date startDate, Date endDate, String status, Long technicalQuestionID, String gitHubLink, String trelloLink, String powerPointPresentation, String functionalRequirements) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.technicalQuestionID = technicalQuestionID;
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

    public Long getTechnicalQuestionID() {
        return technicalQuestionID;
    }

    public void setTechnicalQuestionID(Long technicalQuestionID) {
        this.technicalQuestionID = technicalQuestionID;
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                ", technicalQuestionID=" + technicalQuestionID +
                ", gitHubLink='" + gitHubLink + '\'' +
                ", trelloLink='" + trelloLink + '\'' +
                ", powerPointPresentation='" + powerPointPresentation + '\'' +
                ", functionalRequirements='" + functionalRequirements + '\'' +
                '}';
    }
}