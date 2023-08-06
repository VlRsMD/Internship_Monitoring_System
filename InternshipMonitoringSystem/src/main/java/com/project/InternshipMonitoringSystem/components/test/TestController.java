package com.project.InternshipMonitoringSystem.components.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getTests() {
        logger.info("Request to fetch tests in the system.");
        return testService.getTests();
    }

    @PostMapping(path = "question/{questionId}")
    public void addTest(@RequestBody Test test) {
        logger.info("Request to add a new test into the system.");
        testService.addTest(test);
    }

    @DeleteMapping(path = "{testId}")
    public void deleteTest(@PathVariable("testId") Long testId) {
        logger.info("Request to delete a particular test from the system.");
        testService.deleteTest(testId);
    }

    @PatchMapping(path = "{testId}/question/{questionId}")
    public void changeTestTitle(
            @PathVariable("testId") Long testId,
            @RequestParam(required = false) String testTitle) {
        logger.info("Request to change the test title of a particular test.");
        testService.changeTestTitle(testId, testTitle);
    }
}

