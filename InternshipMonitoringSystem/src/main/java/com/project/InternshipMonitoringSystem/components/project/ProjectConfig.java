package com.project.InternshipMonitoringSystem.components.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(ProjectRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}

