package com.project.InternshipMonitoringSystem.components.mark;

import com.project.InternshipMonitoringSystem.components.candidate.Candidate;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateRepository;
import com.project.InternshipMonitoringSystem.components.test.Test;
import com.project.InternshipMonitoringSystem.components.test.TestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final CandidateRepository candidateRepository;
    private final TestRepository testRepository;

    public MarkService(MarkRepository markRepository, CandidateRepository candidateRepository, TestRepository testRepository) {
        this.markRepository = markRepository;
        this.candidateRepository = candidateRepository;
        this.testRepository = testRepository;
    }

    public List<Mark> getMarks() {
        return markRepository.findAll();
    }

    public void addMark(Mark mark, Long candidateId, Long testId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalStateException("Candidate with ID " + candidateId + " does not exist."));
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalStateException("Test with ID " + testId + " does not exist."));
        mark.setCandidate(candidate);
        mark.setTest(test);
        markRepository.save(mark);
    }

    public void deleteMark(Long markId) {
        boolean exists = markRepository.existsById(markId);
        if (!exists) {
            throw new IllegalStateException("Mark with ID " +markId + " does not exist.");
        }
        markRepository.deleteById(markId);
    }

    @Transactional
    public void changeMarkValue(Long markId, int value) {
        if (value > 0) {
            Mark mark = markRepository.findById(markId)
                    .orElseThrow(() -> new IllegalStateException("Mark with ID " + markId + " does not exist."));
            if(!Objects.equals(mark.getValue(), value)) {
                mark.setValue(value);
            }
        }
    }
}
