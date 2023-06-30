package com.project.InternshipMonitoringSystem.components.screenshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/screenshot")
public class ScreenshotController {
    private final ScreenshotService screenshotService;

    @Autowired
    public ScreenshotController(ScreenshotService screenshotService) {
        this.screenshotService = screenshotService;
    }

    @GetMapping
    public List<Screenshot> getScreenshots() {
        return screenshotService.getScreenshots();
    }

    @PostMapping
    public void registerNewScreenshot(@RequestBody Screenshot screenshot) {
        screenshotService.addNewScreenshot(screenshot);
    }

    @DeleteMapping(path = "{screenshotId}")
    public void deleteScreenshot(@PathVariable("screenshotId") Long screenshotId) {
        screenshotService.deleteScreenshot(screenshotId);
    }

    @PutMapping(path = "{screenshotId}")
    public void updateScreenshot(
            @PathVariable("screenshotId") Long screenshotId,
            @RequestParam(required = false) String screenshotLink) {
        screenshotService.updateScreenshot(screenshotId, screenshotLink);
    }
}
