package com.cg.controllers;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.QuestionDTO;
import com.cg.entities.Question;
import com.cg.exceptions.QuestionNotFoundException;
import com.cg.service.QuestionSeriviceInterface;

@RestController      
@CrossOrigin(origins="*")
public class QuestionController {
	@Autowired
	QuestionSeriviceInterface service;
	
	private static final Logger logger = LogManager.getLogger(QuestionController.class);

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value="/question/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getQuestion()				//Method to get the list of Questions
	{
		logger.info("Entered in question controller method getQuestion");
		List<QuestionDTO> qlist = service.getQuestion();
		logger.info("Done in question controller method getQuestion");

		return  new ResponseEntity(qlist, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value="/question/getID/{qid}")
	public ResponseEntity getQuestionId(@PathVariable("qid")int qid) throws QuestionNotFoundException
	{																//Method to get a Question by question_ID
		logger.info("Entered in question controller method getQuestionId");
		QuestionDTO q=service.getQuestionById(qid);
		logger.info("Done in question controller method getQuestionId");
		return new ResponseEntity(q, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value="/question/deleteID/{qid}")
	public ResponseEntity deleteQuestionbyId(@PathVariable("qid")int qid) throws QuestionNotFoundException
	{														//Method to delete a Question by question_ID
		logger.info("Entered in question controller method deleteQuestionById");
		Question q=service.deleteQuestion(qid);
		logger.info("Done in question controller method deleteQuestionById");
		return new ResponseEntity(q, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping(value="/question/add") 
	public ResponseEntity addQuestion(@RequestBody QuestionDTO questiondto)
	{														//Method to add a Question
		logger.info("Entered in question controller method addQuestion");
		service.addQuestion(questiondto);
		logger.info("Done in question controller method addQuestion");
		return new ResponseEntity(questiondto, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping(value="/question/updateID/{qid}")
	public ResponseEntity updateQuestionbyId(@PathVariable("qid")int qid,@RequestBody QuestionDTO questiondto) throws QuestionNotFoundException
	{														//Method to update a Question by question_ID
		logger.info("Entered in question controller method updateQuestionbyId");
		Question q=service.updateQuestion(qid,questiondto);
		logger.info("Done in question controller method updateQuestionbyId");
		return new ResponseEntity(q, HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(QuestionNotFoundException.class) 					//to handle exception at controller level
	public ResponseEntity<String> handleQuestionNotFoundException(QuestionNotFoundException ex) 
	{
		logger.info("Entered in question controller method handleQuestionNotFoundException");
		logger.info("Done in question controller method handleQuestionNotFoundException");
		return new ResponseEntity("Question Not Found Exception",HttpStatus.OK);
	}	
	
}

