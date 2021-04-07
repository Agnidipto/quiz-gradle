package com.cg.repo;

 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entities.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{

}
