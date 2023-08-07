package com.project.InternshipMonitoringSystem.components.candidate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/candidate")
public class CandidateController {
    private static final Logger logger = LoggerFactory.getLogger(CandidateController.class);

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<CandidateDTO> getCandidates() {
        logger.info("Request to fetch candidates in the system.");
        return candidateService.getCandidates();
    }

    @PostMapping
    public void addCandidate(@RequestBody Candidate candidate) {
        logger.info("Request to add a new candidate into the system.");
        candidateService.addCandidate(candidate);
    }

    @DeleteMapping("{candidateId}")
    public void deleteCandidate(@PathVariable("candidateId") Long candidateId) {
        logger.info("Request to delete a particular candidate from the system.");
        candidateService.deleteCandidate(candidateId);
    }

    @PatchMapping("{candidateId}")
    public void changeCandidateStatus(
            @PathVariable("candidateId") Long candidateId,
            @RequestParam(required = false) String status) {
        logger.info("Request to change the status of a particular candidate.");
        candidateService.changeCandidateStatus(candidateId, status);
    }
}
