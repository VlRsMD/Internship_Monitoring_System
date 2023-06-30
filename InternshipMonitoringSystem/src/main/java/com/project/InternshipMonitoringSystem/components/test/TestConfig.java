package com.project.InternshipMonitoringSystem.components.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestConfig {

    @Bean
    CommandLineRunner commandLineRunner(TestRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}
