package com.project.InternshipMonitoringSystem.components.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/candidate")
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<Candidate> getCandidates() {
        return candidateService.getCandidates();
    }

    @PostMapping
    public void registerNewCandidate(@RequestBody Candidate candidate) {
        candidateService.addNewCandidate(candidate);
    }

    @DeleteMapping(path = "{candidateId}")
    public void deleteCandidate(@PathVariable("candidateId") Long candidateId) {
        candidateService.deleteCandidate(candidateId);
    }

    @PutMapping(path = "{candidateId}")
    public void updateCandidate(
            @PathVariable("candidateId") Long candidateId,
            @RequestParam(required = false) String status) {
        candidateService.updateCandidate(candidateId, status);
    }
}
