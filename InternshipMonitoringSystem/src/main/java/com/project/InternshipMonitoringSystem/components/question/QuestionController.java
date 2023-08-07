package com.project.InternshipMonitoringSystem.components.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question")
public class QuestionController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionDTO> getQuestions() {
        logger.info("Request to fetch questions in the system.");
        return questionService.getQuestions();
    }

    @PostMapping("test/{testId}")
    public void addQuestion(@RequestBody Question question,
                            @PathVariable("testId") Long testId) {
        logger.info("Request to add a new question into the system.");
        questionService.addQuestion(question, testId);
    }

    @DeleteMapping("{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        logger.info("Request to delete a particular question from the system.");
        questionService.deleteQuestion(questionId);
    }

    @PatchMapping("{questionId}")
    public void changeQuestionText(
            @PathVariable("questionId") Long questionId,
            @RequestParam(required = false) String questionText) {
        logger.info("Request to change the question text of a particular question.");
        questionService.changeQuestionText(questionId, questionText);
    }
}
