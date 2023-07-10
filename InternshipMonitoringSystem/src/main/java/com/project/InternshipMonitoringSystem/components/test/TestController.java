package com.project.InternshipMonitoringSystem.components.test;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/test")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getTests() {
        return testService.getTests();
    }

    @PostMapping(path = "question/{questionId}")
    public void addTest(
            @RequestBody Test test,
            @PathVariable("questionId") Long questionId
    ) {
        testService.addTest(test, questionId);
    }

    @DeleteMapping(path = "{testId}")
    public void deleteTest(@PathVariable("testId") Long testId) {
        testService.deleteTest(testId);
    }

    @PatchMapping(path = "{testId}/question/{questionId}")
    public void addQuestionToTheTest(
            @PathVariable("testId") Long testId,
            @PathVariable("questionId") Long questionId
    ) {
        testService.addQuestionToTheTest(testId, questionId);
    }
}

