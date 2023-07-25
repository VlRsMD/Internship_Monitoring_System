package com.project.InternshipMonitoringSystem.components.question;

public class QuestionDTO {
    private Long id;
    private String questionText;
    private Long testId;

    public QuestionDTO(Long id, String questionText, Long testId) {
        this.id = id;
        this.questionText = questionText;
        this.testId = testId;
    }

    public QuestionDTO() {
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

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
