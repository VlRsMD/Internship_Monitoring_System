package com.project.InternshipMonitoringSystem.components.candidate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CandidateConfig {
    @Bean
    CommandLineRunner candidateCommandLineRunner(CandidateRepository repository) {
        return args -> {
            /* Candidate alexander = new Candidate(
               "Alexander",
               "alex1@gmail.com",
               "Java basic knowledge",
               "Accepted"
            );

            Candidate andrew = new Candidate(
                    "Andrew",
                    "andrew1@gmail.com",
                    "SQL basic knowledge",
                    "Accepted"
            )

            repository.saveAll(
                    List.of(alexander, andrew)
            ); */
        };
    }
}
