package com.project.InternshipMonitoringSystem.components.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/test")
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getTests() {
        logger.trace("'getTests' method accessed");
        return testService.getTests();
    }

    @PostMapping(path = "question/{questionId}")
    public void addTest(@RequestBody Test test) {
        logger.trace("'addTest' method accessed");
        testService.addTest(test);
    }

    @DeleteMapping(path = "{testId}")
    public void deleteTest(@PathVariable("testId") Long testId) {
        logger.trace("'deleteTest' method accessed");
        testService.deleteTest(testId);
    }

    @PatchMapping(path = "{testId}/question/{questionId}")
    public void changeTestTitle(
            @PathVariable("testId") Long testId,
            @RequestParam(required = false) String testTitle) {
        logger.trace("'changeTestTitle' method accessed");
        testService.changeTestTitle(testId, testTitle);
    }
}

