package com.project.InternshipMonitoringSystem.components.screenshot;

import jakarta.persistence.*;

@Entity
@Table
public class Screenshot {
    @Id
    @SequenceGenerator(
            name = "screenshot_sequence",
            sequenceName = "screenshot_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "screenshot_sequence"
    )
    private Long id;
    private String screenshotLink;
    private Long projectID;

    public Screenshot() {
    }

    public Screenshot(String screenshotLink, Long projectID) {
        this.screenshotLink = screenshotLink;
        this.projectID = projectID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenshotLink() {
        return screenshotLink;
    }

    public void setScreenshotLink(String screenshotLink) {
        this.screenshotLink = screenshotLink;
    }

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Screenshot{" +
                "id=" + id +
                ", screenshotLink='" + screenshotLink + '\'' +
                ", projectID=" + projectID +
                '}';
    }
}
