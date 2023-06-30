package com.project.InternshipMonitoringSystem.components.mentor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MentorConfig {

    @Bean
    CommandLineRunner commandLineRunner(MentorRepository repository) {
        return args -> {
            repository.saveAll(
                    List.of()
            );
        };
    }
}
