package com.project.InternshipMonitoringSystem.components.mark;

public class MarkDTO {
    private Long id;
    private int value;
    private Long candidateId;
    private Long testId;

    public MarkDTO(Long id, int value, Long candidateId, Long testId) {
        this.id = id;
        this.value = value;
        this.candidateId = candidateId;
        this.testId = testId;
    }

    public MarkDTO() {
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

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
