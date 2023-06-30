package com.project.InternshipMonitoringSystem.components.mentor;

import jakarta.persistence.*;

@Entity
@Table
public class Mentor {
    @Id
    @SequenceGenerator(
            name = "mentor_sequence",
            sequenceName = "mentor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mentor_sequence"
    )
    private Long id;
    private String name;
    private Long projectID;

    public Mentor() {
    }

    public Mentor(String name, Long projectID) {
        this.name = name;
        this.projectID = projectID;
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

    public Long getProjectID() {
        return projectID;
    }

    public void setProjectID(Long projectID) {
        this.projectID = projectID;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", projectID=" + projectID +
                '}';
    }
}
