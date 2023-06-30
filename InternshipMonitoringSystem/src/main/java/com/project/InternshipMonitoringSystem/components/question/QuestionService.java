package com.project.InternshipMonitoringSystem.components.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public void addNewQuestion(Question question) {
        System.out.println(question);
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
    public void updateQuestion(Long questionId, String questionText) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalStateException("Question with ID " + questionId + " does not exist."));
        if (questionText != null &&
                questionText.length() > 0 &&
                !Objects.equals(question.getQuestionText(), questionText)) {
            question.setQuestionText(questionText);
        }
    }
}
