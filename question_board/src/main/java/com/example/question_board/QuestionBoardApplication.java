package com.example.question_board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class QuestionBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionBoardApplication.class, args);
    }

}
