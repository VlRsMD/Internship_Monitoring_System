package com.project.InternshipMonitoringSystem.components.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.InternshipMonitoringSystem.components.project.Project;
import com.project.InternshipMonitoringSystem.components.test.Test;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Question {
    @Id
    @SequenceGenerator(
            name = "question_sequence",
            sequenceName = "question_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "question_sequence"
    )

    private Long id;
    private String questionText;

    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private Set<Project> projects = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "assignedQuestions")
    private Set<Test> tests = new HashSet<>();

    public Question() {
    }

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public Set<Test> getTests() {
        return tests;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + questionText + '\'' +
                '}';
    }
}
