package com.project.InternshipMonitoringSystem.components.question;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @PostMapping
    public void addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
    }

    @DeleteMapping(path = "{questionId}")
    public void deleteQuestion(@PathVariable("questionId") Long questionId) {
        questionService.deleteQuestion(questionId);
    }

    @PatchMapping(path = "{questionId}")
    public void changeQuestionText(
            @PathVariable("questionId") Long questionId,
            @RequestParam(required = false) String questionText) {
        questionService.changeQuestionText(questionId, questionText);
    }
}
