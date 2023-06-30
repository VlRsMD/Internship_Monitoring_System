package com.project.InternshipMonitoringSystem.components.screenshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ScreenshotService {
    private final ScreenshotRepository screenshotRepository;

    @Autowired
    public ScreenshotService(ScreenshotRepository screenshotRepository) {
        this.screenshotRepository = screenshotRepository;
    }

    public List<Screenshot> getScreenshots() {
        return screenshotRepository.findAll();
    }

    public void addNewScreenshot(Screenshot screenshot) {
        System.out.println(screenshot);
        screenshotRepository.save(screenshot);
    }

    public void deleteScreenshot(Long screenshotId) {
        boolean exists = screenshotRepository.existsById(screenshotId);
        if (!exists) {
            throw new IllegalStateException("Screenshot with ID " + screenshotId + " does not exist.");
        }
        screenshotRepository.deleteById(screenshotId);
    }

    @Transactional
    public void updateScreenshot(Long screenshotId, String screenshotLink) {
        Screenshot screenshot = screenshotRepository.findById(screenshotId)
                .orElseThrow(() -> new IllegalStateException("Screenshot with ID " + screenshotId + " does not exist."));
        if (screenshotLink != null &&
                screenshotLink.length() > 0 &&
                !Objects.equals(screenshot.getScreenshotLink(), screenshotLink)) {
            screenshot.setScreenshotLink(screenshotLink);
        }
    }
}
