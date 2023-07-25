package com.project.InternshipMonitoringSystem.components.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.InternshipMonitoringSystem.components.mark.Mark;
import com.project.InternshipMonitoringSystem.components.question.Question;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Test {
    @Id
    @SequenceGenerator(
            name = "test_sequence",
            sequenceName = "test_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "test_sequence"
    )
    private Long id;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private Set<Question> questions = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private Set<Mark> marks = new HashSet<>();

    public Test() {
    }

    public Test(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
