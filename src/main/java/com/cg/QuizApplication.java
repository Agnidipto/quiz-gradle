package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SpringBootApplication
public class QuizApplication {
	private static final Logger logger = LogManager.getLogger(QuizApplication.class);
	public static void main(String[] args) {
		logger.info("In main quizApp file");
		SpringApplication.run(QuizApplication.class, args);
	}

}
