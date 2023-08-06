package com.project.InternshipMonitoringSystem.components.mentor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.InternshipMonitoringSystem.components.feedback.Feedback;
import com.project.InternshipMonitoringSystem.components.project.Project;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "mentor")
    private Set<Feedback> feedbacks = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "project_supervised",
            joinColumns = @JoinColumn(name = "mentor_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> supervisedProjects = new HashSet<>();

    public Mentor() {
    }

    public Mentor(String name) {
        this.name = name;
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

    public Set<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public Set<Project> getSupervisedProjects() {
        return supervisedProjects;
    }

    public void registerProject(Project project) {
        supervisedProjects.add(project);
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
