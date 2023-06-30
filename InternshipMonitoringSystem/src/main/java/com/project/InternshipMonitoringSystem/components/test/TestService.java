package com.project.InternshipMonitoringSystem.components.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class TestService {
    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getTests() {
        return testRepository.findAll();
    }

    public void addNewTest(Test test) {
        System.out.println(test);
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
    public void updateTest(Long testId, String title) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalStateException("Test with ID " + testId + " does not exist."));
        if (title != null &&
                title.length() > 0 &&
                !Objects.equals(test.getTitle(), title)) {
            test.setTitle(title);
        }
    }
}