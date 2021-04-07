package com.cg.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.exceptions.AdminNotFoundException;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.QuestionNotFoundException;
import com.cg.exceptions.QuizNotApplicableException;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.exceptions.ScoreNotFoundException;

@SuppressWarnings({ "rawtypes", "unchecked" }) 
@RestControllerAdvice   //  global exception handling , application level
public class ExceptionController 
{
	
	      //Exception for Question not found
	@ExceptionHandler(QuestionNotFoundException.class)
	public ResponseEntity handleQuestionNotFoundException(QuestionNotFoundException ex) 
	{
		return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
	}
	
	
	@ExceptionHandler(QuizNotFoundException.class)
	public ResponseEntity handleQuizNotFoundException(QuizNotFoundException ex) 
	{
		return new ResponseEntity(ex.getMessage(),HttpStatus.OK);
	}
	
	@ExceptionHandler(QuizNotApplicableException.class)
	public ResponseEntity handleQuizNotApplicableException(QuizNotApplicableException ex)
	{
		return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
	}
	
	
	@ExceptionHandler(ParticipantNotFoundException.class)
	public ResponseEntity handleParticipantNotFoundException(ParticipantNotFoundException ex) {
		return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
	}

			//exception for contest
	@ExceptionHandler(ContestNotFoundException.class)
	public ResponseEntity handleContestNotFoundException(ContestNotFoundException ex)
	{

		return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(AdminNotFoundException.class)
	public ResponseEntity handleAdminNotFoundException(AdminNotFoundException ex) {
		return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
	}
	
	
	@ExceptionHandler(ScoreNotFoundException.class)
	public ResponseEntity handleScoreNotFoundException(ScoreNotFoundException ex) {
		return new ResponseEntity(ex.getMessage(), HttpStatus.OK);
	}
	

	     //Parent for all in-built Exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception ex) 
	{
		return new ResponseEntity("Operation cannot be performed \n\n\n"+ex.getMessage() ,HttpStatus.BAD_REQUEST);	
	}

}
