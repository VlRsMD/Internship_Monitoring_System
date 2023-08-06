package com.project.InternshipMonitoringSystem.components.mark;

import com.project.InternshipMonitoringSystem.components.candidate.Candidate;
import com.project.InternshipMonitoringSystem.components.test.Test;
import jakarta.persistence.*;

@Entity
@Table
public class Mark {
    @Id
    @SequenceGenerator(
            name = "mark_sequence",
            sequenceName = "mark_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mark_sequence"
    )
    private Long id;
    private int value;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "candidateid", referencedColumnName = "id")
    private Candidate candidate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "testid", referencedColumnName = "id")
    private Test test;

    public Mark() {
    }

    public Mark(int value, Long testID, Long candidateID) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", value=" + value +
                ", testID=" + test.getId() +
                ", candidateID=" + candidate.getId() +
                '}';
    }
}