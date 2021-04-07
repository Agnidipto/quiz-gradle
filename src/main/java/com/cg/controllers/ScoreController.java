package com.cg.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ScoreDTO;
import com.cg.entities.Score;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.ScoreNotFoundException;
import com.cg.service.ScoreService;

@RestController
@CrossOrigin(origins="*")
public class ScoreController {
	private static final Logger logger = LogManager.getLogger(ScoreController.class);

	@Autowired
	ScoreService service;
	
	@GetMapping(value="/scores", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Score> getAllScores() {

		logger.info("Entered in score controller method addQuestionToQuiz");
		logger.info("Done in score controller method addQuestionToQuiz");
		return service.getAllScores();
	}
	
	@PostMapping("/scores/new")
	public Score createNewScore(@RequestBody ScoreDTO sdto) {
		logger.info("Entered in score controller method createNewScore");
		logger.info("Done in score controller method createNewScore");
		return service.createNewScore(sdto);
	}
	
	@DeleteMapping("/scores/{scoreid}") 
	public String deleteScore(@PathVariable int scoreid) throws ScoreNotFoundException {
		logger.info("Entered in score controller method deleteScore");
		service.deleteScore(scoreid);
		logger.info("Done in score controller method deleteScore");
		return "Score deleted";
	}
	
	@GetMapping("/scores/{scoreid}")
	public Score getScoreById(@PathVariable int scoreid) throws ScoreNotFoundException {
		logger.info("Entered in score controller method getScoreById");
		logger.info("Done in score controller method getScoreById");
		return service.getScoreById(scoreid);
	}
	
	@PutMapping("/scores/{scoreid}")
	public Score updateScore(@PathVariable int scoreid, @RequestBody ScoreDTO sdto) throws ScoreNotFoundException {
		logger.info("Entered in score controller method updateScore");
		logger.info("Done in score controller method updateScore");
		return service.updateScore(scoreid, sdto);
	}
	
	//score will get updated after submit is clicked
	//this will also send a rank via ScoreDTO
	@PutMapping("/scores/new/{contestid}/{participantid}")
	public Score makeNewScore(@RequestBody ScoreDTO sdto, @PathVariable int contestid, @PathVariable int participantid) throws ParticipantNotFoundException, ContestNotFoundException {
		logger.info("Entered in score controller method makeNewScore");
		logger.info("Done in score controller method makeNewScore");
		return service.makeNewScore(sdto, contestid, participantid);
	}
	
	@GetMapping("/participant/{pid}/scores")
	public List<Score> showScoreForParticipant(@PathVariable int pid) throws ParticipantNotFoundException {
		logger.info("Entered in score controller method showScoreForParticipant");
		logger.info("Done in score controller method showScoreForParticipant");
		return service.showScoreForParticipant(pid);
	}
	
	@GetMapping("/contest/{contestid}/scores")
	public List<Score> showScoreForContest(@PathVariable int contestid) throws ContestNotFoundException {
		logger.info("Entered in score controller method showScoreForContest");
		logger.info("Done in score controller method showScoreForContest");
		return service.showScoreForContest(contestid);
	}
	
	
}
