package com.project.InternshipMonitoringSystem.components.test;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TestService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    public void addTest(Test test) {
        testRepository.save(test);
    }

    public void deleteTest(Long testId) {
        boolean exists = testRepository.existsById(testId);
        if (!exists) {
            logger.error("Test with ID " + testId + " does not exist. Unable to delete a nonexistent test.");
            throw new IllegalStateException("Test with ID " + testId + " does not exist. Unable to delete a nonexistent test.");
        }
        testRepository.deleteById(testId);
    }

    @Transactional
    public void changeTestTitle(Long testId, String testTitle) {
        if (testTitle == null || testTitle.length() == 0) {
            logger.error("Invalid test title. Test title cannot be empty.");
            return;
        }
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> {
                    logger.error("Test with ID " + testId + " does not exist. Unable to change the title of a nonexistent test.");
                    throw new IllegalStateException("Test with ID " + testId + " does not exist. Unable to change the title of a nonexistent test.");
                });
        if(!Objects.equals(test.getTitle(), testTitle)) {
            test.setTitle(testTitle);
            logger.info("New title has been assigned to the test with ID " + testId + ".");
        }
    }
}