package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Score;

public interface ScoreRepo extends JpaRepository<Score, Integer> {

}
