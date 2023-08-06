package com.project.InternshipMonitoringSystem.components.question;

import com.project.InternshipMonitoringSystem.components.candidate.CandidateController;
import com.project.InternshipMonitoringSystem.components.test.Test;
import com.project.InternshipMonitoringSystem.components.test.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

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
                .orElseThrow(() -> {
                    logger.error("Test with ID " + testId + " does not exist. Unable to create a new question.");
                    throw new IllegalStateException("Test with ID " + testId + " does not exist. Unable to create a new question.");
                });
        question.setTest(test);
        questionRepository.save(question);
    }

    public void deleteQuestion(Long questionId) {
        boolean exists = questionRepository.existsById(questionId);
        if (!exists) {
            logger.error("Question with ID " + questionId + " does not exist. Unable to delete a nonexistent question.");
            throw new IllegalStateException("Question with ID " + questionId + " does not exist. Unable to delete a nonexistent question.");
        }
        questionRepository.deleteById(questionId);
    }

    @Transactional
    public void changeQuestionText(Long questionId, String questionText) {
        if (questionText == null || questionText.length() == 0) {
            logger.error("Invalid question text. Question text cannot be empty.");
        }
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> {
                    logger.error("Question with ID " + questionId + " does not exist. Unable to change the question text of a nonexistent question.");
                    throw new IllegalStateException("Question with ID " + questionId + " does not exist. Unable to change the question text of a nonexistent question.");
                });
        if(!Objects.equals(question.getQuestionText(), questionText)) {
            question.setQuestionText(questionText);
            logger.info("New text has been assigned to the question with ID " + questionId + ".");
        }
    }
}
