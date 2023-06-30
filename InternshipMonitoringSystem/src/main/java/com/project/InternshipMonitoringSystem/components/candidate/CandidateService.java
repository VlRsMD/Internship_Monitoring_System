package com.project.InternshipMonitoringSystem.components.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public void addNewCandidate(Candidate candidate) {
        Optional<Candidate> candidateOptional = candidateRepository.findCandidateByEmailAddress(candidate.getEmailAddress());
        if (candidateOptional.isPresent()) {
            throw new IllegalStateException("This email address has already been taken.");
        }
        System.out.println(candidate);
        candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long candidateId) {
        boolean exists = candidateRepository.existsById(candidateId);
        if (!exists) {
            throw new IllegalStateException("Candidate with ID " + candidateId + " does not exist.");
        }
        candidateRepository.deleteById(candidateId);
    }

    @Transactional
    public void updateCandidate(Long candidateId, String status) {
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new IllegalStateException("Candidate with ID " + candidateId + " does not exist."));
        if (status != null &&
                status.length() > 0 &&
                !Objects.equals(candidate.getStatus(), status)) {
            candidate.setStatus(status);
        }
    }
}
