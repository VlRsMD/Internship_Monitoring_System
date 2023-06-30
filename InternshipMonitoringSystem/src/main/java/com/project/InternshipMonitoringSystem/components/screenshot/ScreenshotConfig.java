package com.project.InternshipMonitoringSystem.components.screenshot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ScreenshotConfig {

    @Bean
    CommandLineRunner commandLineRunner(ScreenshotRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}
