package com.project.InternshipMonitoringSystem.components.question;

import com.project.InternshipMonitoringSystem.components.test.Test;
import com.project.InternshipMonitoringSystem.components.test.TestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;

    public QuestionService(QuestionRepository questionRepository,
                           TestRepository testRepository) {
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    public List<QuestionDTO> getQuestions() {
        List<Question> questionsList = questionRepository.findAll();
        return questionsList.stream().map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public QuestionDTO fromEntityToDTO(Question question) {
        return new QuestionDTO(question.getId(), question.getQuestionText(), question.getTest().getId());
    }

    public void addQuestion(Question question, Long testId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalStateException("Test with ID " + testId + " does not exist."));
        question.setTest(test);
        questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        boolean exists = questionRepository.existsById(questionId);
        if (!exists) {
            throw new IllegalStateException("Question with ID " + questionId + " does not exist.");
        }
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public void changeQuestionText(Long questionId, String questionText) {
        if (questionText != null && questionText.length() > 0) {
            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new IllegalStateException("Question with ID " + questionId + " does not exist."));
            if(!Objects.equals(question.getQuestionText(), questionText)) {
                question.setQuestionText(questionText);
            }
        }
    }
}
