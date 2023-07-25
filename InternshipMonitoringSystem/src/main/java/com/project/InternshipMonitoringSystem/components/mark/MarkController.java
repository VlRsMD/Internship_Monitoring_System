package com.project.InternshipMonitoringSystem.components.mark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mark")
public class MarkController {
    Logger logger = LoggerFactory.getLogger(MarkController.class);

    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping
    public List<MarkDTO> getMarks() {
        logger.trace("'getMarks' method accessed");
        return markService.getMarks();
    }

    @PostMapping(path = "candidate/{candidateId}/test/{testId}")
    public void addMark(
            @RequestBody Mark mark,
            @PathVariable("candidateId") Long candidateId,
            @PathVariable("testId") Long testId) {
        logger.trace("'addMark' method accessed");
        markService.addMark(mark, candidateId, testId);
    }

    @DeleteMapping(path = "{markId}")
    public void deleteMark(@PathVariable("markId") Long markId) {
        logger.trace("'deleteMark' method accessed");
        markService.deleteMark(markId);
    }

    @PatchMapping(path = "{markId}")
    public void changeMarkValue(
            @PathVariable("markId") Long markId,
            @RequestParam(required = false) int value) {
        logger.trace("'changeMarkValue' method accessed");
        markService.changeMarkValue(markId, value);
    }
}
