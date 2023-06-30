package com.project.InternshipMonitoringSystem.components.mark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/mark")
public class MarkController {
    private final MarkService markService;

    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @GetMapping
    public List<Mark> getMarks() {
        return markService.getMarks();
    }

    @PostMapping
    public void registerNewMark(@RequestBody Mark mark) {
        markService.addNewMark(mark);
    }

    @DeleteMapping(path = "{markId}")
    public void deleteMark(@PathVariable("markId") Long markId) {
        markService.deleteMark(markId);
    }

    @PutMapping(path = "{markId}")
    public void updateMark(
            @PathVariable("markId") Long markId,
            @RequestParam(required = false) int value) {
        markService.updateMark(markId, value);
    }
}
