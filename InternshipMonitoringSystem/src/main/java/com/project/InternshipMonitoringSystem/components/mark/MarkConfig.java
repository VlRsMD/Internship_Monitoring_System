package com.project.InternshipMonitoringSystem.components.mark;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MarkConfig {
    @Bean
    CommandLineRunner markCommandLineRunner(MarkRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}
