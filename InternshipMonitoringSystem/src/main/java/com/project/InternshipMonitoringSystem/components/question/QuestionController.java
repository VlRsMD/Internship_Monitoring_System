package com.project.InternshipMonitoringSystem.components.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/question")
public class QuestionController {
    Logger logger = LoggerFactory.getLogger(QuestionController.class);

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<QuestionDTO> getQuestions() {
        logger.trace("'getQuestions' method accessed");
        return questionService.getQuestions();
    }

    @PostMapping(path = "test/{testId}")
    public void addQuestion(@RequestBody Question question,
                            @PathVariable("testId") Long testId) {
        logger.trace("'addQuestion' method accessed");
        questionService.addQuestion(question, testId);
    }

    @DeleteMapping(path = "{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        logger.trace("'deleteQuestion' method accessed");
        questionService.deleteQuestion(questionId);
    }

    @PatchMapping(path = "{questionId}")
    public void changeQuestionText(
            @PathVariable("questionId") Long questionId,
            @RequestParam(required = false) String questionText) {
        logger.trace("'changeQuestionText' method accessed");
        questionService.changeQuestionText(questionId, questionText);
    }
}
