package com.project.InternshipMonitoringSystem.components.mark;

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
    private Long testID;
    private Long candidateID;

    public Mark() {
    }

    public Mark(int value, Long testID, Long candidateID) {
        this.value = value;
        this.testID = testID;
        this.candidateID = candidateID;
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

    public Long getTestID() {
        return testID;
    }

    public void setTestID(Long testID) {
        this.testID = testID;
    }

    public Long getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(Long candidateID) {
        this.candidateID = candidateID;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", value=" + value +
                ", testID=" + testID +
                ", candidateID=" + candidateID +
                '}';
    }
}