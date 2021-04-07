package com.cg.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ContestDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Contest;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.service.ContestServiceImpl;

@RestController
@RequestMapping("/creator")
@CrossOrigin(origins="*")
public class ContestController {
	private static final Logger logger = LogManager.getLogger(ContestController.class);

	@Autowired
	ContestServiceImpl contestService;
	
	@GetMapping(value="contest", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ContestDTO> getContests(){
		logger.info("Entered in contest controller method getContests");
		ArrayList<ContestDTO> contestDTOList = contestService.getContests();
		logger.info("Done in contest controller method getContests");
		return contestDTOList;
	}
	
	@GetMapping(value="contest/{contestID}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ContestDTO getContest(@PathVariable Integer contestID) throws ContestNotFoundException{
		logger.info("Entered in contest controller method getContest");
		ContestDTO contestDTOId = contestService.getContest(contestID);
		logger.info("Done in contest controller method getContest");
		return contestDTOId;
	}
	
	@DeleteMapping(value="contest/{contestID}")
	public boolean removeContest(@PathVariable Integer contestID) throws ContestNotFoundException{
		logger.info("Entered in contest controller method removeContest");
		logger.info("Done in contest controller method removeContest");
		return contestService.deleteContest(contestID);
	}
	
	@PutMapping(value="contest/{contestID}")
	public ContestDTO updateContest(@PathVariable Integer contestID, @RequestBody ContestDTO contestDTO) throws ContestNotFoundException {
		logger.info("Entered in contest controller method updateContest");
		ContestDTO updateContest = contestService.updateContest(contestID, contestDTO);
		logger.info("Done in contest controller method updateContest");
		return updateContest;
	}
	
	@PostMapping(value="contest")
	public ContestDTO addContest(@RequestBody ContestDTO contestDTO) {
		logger.info("Entered in contest controller method addContest");
		logger.info("Done in contest controller method addContest");
		return contestService.addContest(contestDTO);
	}
	

	//for adding quizzes to our contest
	@PostMapping(value = "contest/{contestID}/add/quiz")
	public ResponseEntity<ContestDTO> addQuizToContest(@PathVariable Integer contestID, @RequestBody List<QuizDTO> quiz) throws ContestNotFoundException {
		logger.info("Entered in contest controller method addQuizToContest");
		ContestDTO contestDTO = contestService.addQuizToContest(contestID, quiz);
		logger.info("Done in contest controller method addQuizToContest");
		return new ResponseEntity<ContestDTO>(contestDTO, HttpStatus.OK);

	}
	
}
