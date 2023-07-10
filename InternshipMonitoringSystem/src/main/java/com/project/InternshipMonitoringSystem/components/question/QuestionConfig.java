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
            Question question1 = new Question(
                    "What is encapsulation?"
            );

            Question question2 = new Question(
                    "Write Java code to print out all prime numbers <= 29."
            );

            repository.saveAll(
                    List.of(question1, question2)
            );
        };
    }
}
