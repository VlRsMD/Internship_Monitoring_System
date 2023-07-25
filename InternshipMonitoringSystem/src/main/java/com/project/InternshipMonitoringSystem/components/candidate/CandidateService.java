package com.project.InternshipMonitoringSystem.components.candidate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {
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
            throw new IllegalStateException("This email address has already been taken.");
        }
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
    public void changeCandidateStatus(Long candidateId, String status) {
        if (status != null && status.length() > 0) {
            Candidate candidate = candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new IllegalStateException("Candidate with ID " + candidateId + " does not exist."));
            if(!Objects.equals(candidate.getStatus(), status)) {
                candidate.setStatus(status);
            }
        }
    }
}
