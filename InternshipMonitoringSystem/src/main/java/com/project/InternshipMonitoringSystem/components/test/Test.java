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

    @ManyToMany
    @JoinTable(
            name = "assigned_questions",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> assignedQuestions = new HashSet<>();

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

    public Set<Question> getAssignedQuestions() {
        return assignedQuestions;
    }

    public void registerQuestion(Question question) {
        assignedQuestions.add(question);
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
