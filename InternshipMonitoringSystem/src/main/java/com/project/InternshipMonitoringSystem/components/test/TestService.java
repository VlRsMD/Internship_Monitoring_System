package com.project.InternshipMonitoringSystem.components.test;

import com.project.InternshipMonitoringSystem.components.question.Question;
import com.project.InternshipMonitoringSystem.components.question.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    public TestService(TestRepository testRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;
        this.questionRepository = questionRepository;
    }

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    public void addTest(Test test, Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalStateException("Project with ID " + questionId + " does not exist."));
        test.registerQuestion(question);
        testRepository.save(test);
    }

    public void deleteTest(Long testId) {
        boolean exists = testRepository.existsById(testId);
        if (!exists) {
            throw new IllegalStateException("Test with ID " + testId + " does not exist.");
        }
        testRepository.deleteById(testId);
    }

    @Transactional
    public void addQuestionToTheTest(Long testId, Long questionId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalStateException("Test with ID " + testId + " does not exist."));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalStateException("Project with ID " + questionId + " does not exist."));
        test.registerQuestion(question);
    }
}