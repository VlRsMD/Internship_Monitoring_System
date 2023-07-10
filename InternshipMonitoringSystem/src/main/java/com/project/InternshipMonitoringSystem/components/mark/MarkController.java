package com.project.InternshipMonitoringSystem.components.mark;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mark")
public class MarkController {
    private final MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping
    public List<Mark> getMarks() {
        return markService.getMarks();
    }

    @PostMapping(path = "candidate/{candidateId}/test/{testId}")
    public void addMark(
            @RequestBody Mark mark,
            @PathVariable("candidateId") Long candidateId,
            @PathVariable("testId") Long testId
    ) {
        markService.addMark(mark, candidateId, testId);
    }

    @DeleteMapping(path = "{markId}")
    public void deleteMark(@PathVariable("markId") Long markId) {
        markService.deleteMark(markId);
    }

    @PatchMapping(path = "{markId}")
    public void changeMarkValue(
            @PathVariable("markId") Long markId,
            @RequestParam(required = false) int value) {
        markService.changeMarkValue(markId, value);
    }
}
