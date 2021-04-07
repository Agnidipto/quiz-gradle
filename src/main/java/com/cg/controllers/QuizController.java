package com.cg.controllers;

import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.service.QuizService;

@RestController
@SuppressWarnings({ "unchecked", "rawtypes" })
@CrossOrigin(origins="*")
public class QuizController {
	private static final Logger logger = LogManager.getLogger(QuizController.class);

	@Autowired
	QuizService service;

	// get all quiz
	@GetMapping(value = "quiz", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<QuizDTO>> getQuiz() {
		logger.info("Entered in quiz controller method getQuiz");
		List<QuizDTO> quizlist = service.getAllQuiz();
		logger.info("Done in quiz controller method getQuiz");
		return new ResponseEntity(quizlist, HttpStatus.OK);
	}

	// get quiz by ID
	@GetMapping(value = "quiz/{quiz_id}")
	public ResponseEntity<QuizDTO> getQuiz(@PathVariable("quiz_id") int quiz_id) throws QuizNotFoundException {
		logger.info("Entered in quiz controller method getQuiz");
		QuizDTO quiz = service.getQuiz(quiz_id);
		logger.info("Done in quiz controller method getQuiz");
		return new ResponseEntity(quiz, HttpStatus.OK);
	}

	// add quiz
	@PostMapping(value = "quiz")
	public ResponseEntity<QuizDTO> addQuiz(@RequestBody QuizDTO quizdto) {
		logger.info("Entered in quiz controller method addQuiz");
		QuizDTO quiz = service.addQuiz(quizdto);
		logger.info("Done in quiz controller method getQuiz");
		return new ResponseEntity(quiz, HttpStatus.OK);
	}

	// update quiz by id
	@PutMapping(value = "quiz/{quiz_id}")
	public ResponseEntity<QuizDTO> updateQuiz(@PathVariable Integer quiz_id, @RequestBody QuizDTO quizdto)
			throws QuizNotFoundException {
		logger.info("Entered in quiz controller method updateQuiz");
		QuizDTO quiz = service.updateQuiz(quiz_id, quizdto);
		logger.info("Done in quiz controller method updateQuiz");
		return new ResponseEntity(quiz, HttpStatus.OK);
	}

	// delete quiz by id
	@DeleteMapping(value = "quiz/{quiz_id}")
	public boolean deleteQuiz(@PathVariable Integer quiz_id) throws QuizNotFoundException {
		logger.info("Entered in quiz controller method deleteQuiz");
		boolean deleted = service.deleteQuiz(quiz_id);
		logger.info("Done in quiz controller method deleteQuiz");
		return deleted;
	}

	// add questions to quiz by quiz id
	@PostMapping(value = "quiz/{quiz_id}/question")
	public ResponseEntity<QuizDTO> addQuestionToQuiz(@PathVariable Integer quiz_id,
			@RequestBody Set<QuestionDTO> questions) throws QuizNotFoundException {
		logger.info("Entered in quiz controller method addQuestionToQuiz");
		QuizDTO quiz = service.addQuestionToQuiz(quiz_id, questions);
		logger.info("Done in quiz controller method addQuestionToQuiz");
		return new ResponseEntity(quiz, HttpStatus.OK);
	}

}
