package com.project.InternshipMonitoringSystem.components.mentor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MentorConfig {
    @Bean
    CommandLineRunner mentorCommandLineRunner(MentorRepository repository) {
        return args -> {
            /* Mentor john = new Mentor(
                    "John"
            );

            Mentor william = new Mentor(
                    "William"
            );

            repository.saveAll(
                    List.of(john, william)
            ); */
        };
    }
}
