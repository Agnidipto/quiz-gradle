package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, Integer> {
	
}
