package com.project.InternshipMonitoringSystem.components.test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TestService {
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
            throw new IllegalStateException("Test with ID " + testId + " does not exist.");
        }
        testRepository.deleteById(testId);
    }

    @Transactional
    public void changeTestTitle(Long testId, String testTitle) {
        if (testTitle != null && testTitle.length() > 0) {
            Test test = testRepository.findById(testId)
                    .orElseThrow(() -> new IllegalStateException("Test with ID " + testId + " does not exist."));
            if(!Objects.equals(test.getTitle(), testTitle)) {
                test.setTitle(testTitle);
            }
        }
    }
}