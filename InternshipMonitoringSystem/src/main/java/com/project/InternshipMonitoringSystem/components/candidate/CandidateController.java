package com.project.InternshipMonitoringSystem.components.candidate;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/candidate")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<Candidate> getCandidates() {
        return candidateService.getCandidates();
    }

    @PostMapping
    public void addCandidate(@RequestBody Candidate candidate) {
        candidateService.addCandidate(candidate);
    }

    @DeleteMapping("{candidateId}")
    public void deleteCandidate(@PathVariable("candidateId") Long candidateId) {
        candidateService.deleteCandidate(candidateId);
    }

    @PatchMapping("{candidateId}")
    public void changeCandidateStatus(
            @PathVariable("candidateId") Long candidateId,
            @RequestParam(required = false) String status) {
        candidateService.changeCandidateStatus(candidateId, status);
    }
}
