package com.cg.service;

import java.util.List;

import com.cg.dto.QuestionDTO;
import com.cg.entities.Question;
import com.cg.exceptions.QuestionNotFoundException;

public interface QuestionSeriviceInterface {
	
	public List<QuestionDTO> getQuestion();
	
	public QuestionDTO getQuestionById(int id) throws QuestionNotFoundException;
	
	public Question deleteQuestion(int id) throws QuestionNotFoundException;
	
	public Question addQuestion(QuestionDTO questiondto);
	
	public Question updateQuestion(int id,QuestionDTO questiondto) throws QuestionNotFoundException;
}
