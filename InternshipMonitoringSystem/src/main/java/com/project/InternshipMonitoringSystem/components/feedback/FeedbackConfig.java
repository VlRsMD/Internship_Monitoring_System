package com.project.InternshipMonitoringSystem.components.feedback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FeedbackConfig {
    @Bean
    CommandLineRunner feedbackCommandLineRunner(FeedbackRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}
