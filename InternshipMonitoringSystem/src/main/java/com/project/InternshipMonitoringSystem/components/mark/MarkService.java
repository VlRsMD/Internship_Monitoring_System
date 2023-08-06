package com.project.InternshipMonitoringSystem.components.mark;

import com.project.InternshipMonitoringSystem.components.candidate.Candidate;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateController;
import com.project.InternshipMonitoringSystem.components.candidate.CandidateRepository;
import com.project.InternshipMonitoringSystem.components.test.Test;
import com.project.InternshipMonitoringSystem.components.test.TestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MarkService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final MarkRepository markRepository;
    private final CandidateRepository candidateRepository;
    private final TestRepository testRepository;

    public MarkService(MarkRepository markRepository,
                       CandidateRepository candidateRepository,
                       TestRepository testRepository) {
        this.markRepository = markRepository;
        this.candidateRepository = candidateRepository;
        this.testRepository = testRepository;
    }

    public List<MarkDTO> getMarks() {
        List<Mark> marksList = markRepository.findAll();
        return marksList.stream().map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public MarkDTO fromEntityToDTO(Mark mark) {
        return new MarkDTO(mark.getId(), mark.getValue(), mark.getCandidate().getId(), mark.getTest().getId());
    }

    public void addMark(Mark mark, Long candidateId, Long testId) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    logger.error("Candidate with ID " + candidateId + " does not exist. Unable to create a new mark.");
                    throw new IllegalStateException("Candidate with ID " + candidateId + " does not exist. Unable to create a new mark.");
                });
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> {
                    logger.error("Test with ID " + testId + " does not exist. Unable to create a new mark.");
                    throw new IllegalStateException("Test with ID " + testId + " does not exist. Unable to create a new mark.");
                });
        mark.setCandidate(candidate);
        mark.setTest(test);
        markRepository.save(mark);
    }

    public void deleteMark(Long markId) {
        boolean exists = markRepository.existsById(markId);
        if (!exists) {
            logger.error("Mark with ID " +markId + " does not exist. Unable to delete a nonexistent mark.");
            throw new IllegalStateException("Mark with ID " +markId + " does not exist. Unable to delete a nonexistent mark.");
        }
        markRepository.deleteById(markId);
    }

    @Transactional
    public void changeMarkValue(Long markId, int value) {
        if (value <= 0) {
            logger.error("Invalid value. Value cannot be smaller or equal to 0.");
            return;
        }
        Mark mark = markRepository.findById(markId)
                .orElseThrow(() -> {
                    logger.error("Mark with ID " + markId + " does not exist. Unable to change the value of a nonexistent mark.");
                    throw new IllegalStateException("Mark with ID " + markId + " does not exist. Unable to change the value of a nonexistent mark.");
                });
        if(!Objects.equals(mark.getValue(), value)) {
            mark.setValue(value);
            logger.info("New value has been assigned to the mark with ID " + markId + ".");
        }
    }
}
