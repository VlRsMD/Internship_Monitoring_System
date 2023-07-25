package com.project.InternshipMonitoringSystem.components.candidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/candidate")
public class CandidateController {
    Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateDTO> getCandidates() {
        logger.trace("'getCandidates' method accessed");
        return candidateService.getCandidates();
    }

    @PostMapping
    public void addCandidate(@RequestBody Candidate candidate) {
        logger.trace("'addCandidate' method accessed");
        candidateService.addCandidate(candidate);
    }

    @DeleteMapping("{candidateId}")
    public void deleteCandidate(@PathVariable("candidateId") Long candidateId) {
        logger.trace("'deleteCandidate' method accessed");
        candidateService.deleteCandidate(candidateId);
    }

    @PatchMapping("{candidateId}")
    public void changeCandidateStatus(
            @PathVariable("candidateId") Long candidateId,
            @RequestParam(required = false) String status) {
        logger.trace("'changeCandidateStatus' method accessed");
        candidateService.changeCandidateStatus(candidateId, status);
    }
}
