package com.project.InternshipMonitoringSystem.components.mark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mark")
public class MarkController {
    private static final Logger logger = LoggerFactory.getLogger(MarkController.class);

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping
    public List<MarkDTO> getMarks() {
        logger.info("Request to fetch marks in the system.");
        return markService.getMarks();
    }

    @PostMapping(path = "candidate/{candidateId}/test/{testId}")
    public void addMark(
            @RequestBody Mark mark,
            @PathVariable("candidateId") Long candidateId,
            @PathVariable("testId") Long testId) {
        logger.info("Request to add a new mark into the system.");
        markService.addMark(mark, candidateId, testId);
    }

    @DeleteMapping(path = "{markId}")
    public void deleteMark(@PathVariable("markId") Long markId) {
        logger.info("Request to delete a particular mark from the system.");
        markService.deleteMark(markId);
    }

    @PatchMapping(path = "{markId}")
    public void changeMarkValue(
            @PathVariable("markId") Long markId,
            @RequestParam(required = false) int value) {
        logger.info("Request to change the mark value of a particular mark.");
        markService.changeMarkValue(markId, value);
    }
}
