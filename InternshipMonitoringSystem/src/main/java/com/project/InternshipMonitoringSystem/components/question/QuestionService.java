package com.project.InternshipMonitoringSystem.components.question;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getQuestions() {
        return questionRepository.findAll();
    }

    public void addQuestion(Question question) {
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
