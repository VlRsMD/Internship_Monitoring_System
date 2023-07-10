package com.project.InternshipMonitoringSystem.components.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TestConfig {
    @Bean
    CommandLineRunner testCommandLineRunner(TestRepository repository) {
        return args -> {
            Test test1 = new Test(
                    "Java Test"
            );

            Test test2 = new Test(
                    "English Test"
            );

            repository.saveAll(
                    List.of(test1, test2)
            );
        };
    }
}
