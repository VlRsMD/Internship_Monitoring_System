package com.project.InternshipMonitoringSystem.components.question;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuestionConfig {
    @Bean
    CommandLineRunner questionCommandLineRunner(QuestionRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}
