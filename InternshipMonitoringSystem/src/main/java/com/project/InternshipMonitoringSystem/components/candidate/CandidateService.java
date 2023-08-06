package com.project.InternshipMonitoringSystem.components.candidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<CandidateDTO> getCandidates() {
        List<Candidate> candidatesList = candidateRepository.findAll();
        return candidatesList.stream().map(this::fromEntityToDTO)
                .collect(Collectors.toList());
    }

    public CandidateDTO fromEntityToDTO(Candidate candidate) {
        return new CandidateDTO(candidate.getId(), candidate.getName(), candidate.getStatus());
    }

    public void addCandidate(Candidate candidate) {
        Optional<Candidate> candidateOptional = candidateRepository.findCandidateByEmailAddress(candidate.getEmailAddress());
        if (candidateOptional.isPresent()) {
            logger.error("This email address has already been taken. Unable to create a new candidate with the given email address.");
            throw new IllegalStateException("This email address has already been taken. Unable to create a new candidate with the given email address.");
        }
        candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long candidateId) {
        boolean exists = candidateRepository.existsById(candidateId);
        if (!exists) {
            logger.error("Candidate with ID " + candidateId + " does not exist. Unable to delete a nonexistent candidate.");
            throw new IllegalStateException("Candidate with ID " + candidateId + " does not exist. Unable to delete a nonexistent candidate.");
        }
        candidateRepository.deleteById(candidateId);
    }

    @Transactional
    public void changeCandidateStatus(Long candidateId, String status) {
        if (status == null || status.length() == 0) {
            logger.error("Invalid status. Status cannot be empty.");
            return;
        }
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    logger.error("Candidate with ID " + candidateId + " does not exist. Unable to change the status of a nonexistent candidate.");
                    throw new IllegalStateException("Candidate with ID " + candidateId + " does not exist. Unable to change the status of a nonexistent candidate.");
                });
        if(!Objects.equals(candidate.getStatus(), status)) {
                candidate.setStatus(status);
                logger.info("New status has been assigned to the candidate with ID " + candidateId + ".");
        }
    }
}
