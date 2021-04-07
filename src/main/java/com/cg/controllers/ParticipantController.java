package com.cg.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ParticipantDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Contest;
import com.cg.entities.Participant;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.QuizNotApplicableException;
import com.cg.service.ParticipantService;

@RestController
@CrossOrigin(origins="*")
public class ParticipantController {
	private static final Logger logger = LogManager.getLogger(ParticipantController.class);

	@Autowired
	ParticipantService pservice;

	@GetMapping("/participant")

	public List<Participant> getAllParticipants(){
		logger.info("Entered in creator controller method getAllParticipants");
		logger.info("Done in creator controller method getAllParticipants");
		return pservice.getAllParticipants();
	}
	
	@PostMapping("/participant/new")
	public Participant createNewParticipant(@RequestBody ParticipantDTO pdto) {
		logger.info("Entered in creator controller method createNewParticipants");
		logger.info("Done in creator controller method createNewParticipants");
		return pservice.createNewParticipant(pdto);
	}
	
	@GetMapping("/participant/{pid}")
	public Participant getParticipant(@PathVariable int pid) throws ParticipantNotFoundException{

		logger.info("Entered in creator controller method getAllParticipant");
		logger.info("Done in creator controller method getAllParticipant");
		return pservice.getParticipantById(pid);
	}
	
	@DeleteMapping("/participant/{pid}")

	public Participant deleteParticipant(@PathVariable int pid) throws ParticipantNotFoundException {
		logger.info("Entered in creator controller method deleteParticipant");
		Participant p=pservice.getParticipantById(pid);
		pservice.deleteParticipant(pid);
		logger.info("Done in creator controller method deleteParticipant");
		return p;
	}
	
	@PutMapping("/participant/{pid}")
	public Participant updateContest(@PathVariable int pid, @RequestBody ParticipantDTO pdto) throws ParticipantNotFoundException{
		logger.info("Entered in creator controller method updateContest");
		logger.info("Done in creator controller method updateContest");
		return pservice.updateContest(pid, pdto);
	}
	
	@GetMapping("/participant/{pid}/contest")
	public List<Contest> showContestsOfParticipant(@PathVariable int pid) throws ParticipantNotFoundException
	{
		logger.info("Entered in creator controller method showContestsOfParticipant");
		logger.info("Done in creator controller method showContestsOfParticipant");
		return pservice.getContestWithParticipantID(pid);
	}
	
	@PutMapping("/participant/{pid}/contest/{contestid}")
	public Participant mapParticipantToContest(@PathVariable int pid, @PathVariable int contestid) throws ParticipantNotFoundException, ContestNotFoundException
	{
		logger.info("Entered in creator controller method mapParticipantToContest");
		logger.info("Done in creator controller method mapParticipantToContest");
		return pservice.mapParticipantToContest(pid, contestid);
	}
	
	@GetMapping("/participant/{pid}/contest/{contestid}/{round_no}/start")
	public QuizDTO startQuiz(@PathVariable int pid, @PathVariable int contestid, @PathVariable int roundno) throws ParticipantNotFoundException, ContestNotFoundException, QuizNotApplicableException {
		logger.info("Entered in creator controller method startQuiz");
		logger.info("Done in creator controller method startQuiz");
		return pservice.startQuiz(pid, contestid, roundno);
	}

	
	@GetMapping("/participant/{pid}/contest/{contestid}/{round_no}/submit")
	public String submitQuiz(@PathVariable int pid, @PathVariable int contestid, @PathVariable int roundno) throws ParticipantNotFoundException, ContestNotFoundException {
		logger.info("Entered in creator controller method submitQuiz");
		logger.info("Done in creator controller method submitQuiz");
		return pservice.submitQuiz(pid, contestid, roundno);
	}

	
	@GetMapping("/participant/{pid}/contest/{contestid}/eliminated")
	public String eliminated(@PathVariable int pid, @PathVariable int contestid) throws ContestNotFoundException, ParticipantNotFoundException {
		logger.info("Entered in creator controller method eliminated");
		logger.info("Done in creator controller method eliminated");
		return pservice.eliminatedDisplay(pid, contestid);
	}
	
	@GetMapping("/participant/{pid}/contest/{contestid}/{round_no}/wait")
	public String waitForResponse(@PathVariable int pid, @PathVariable int contestid, @PathVariable int roundno) {
		logger.info("Entered in creator controller method waitForResponse");
		logger.info("Done in creator controller method waitForResponse");
		return "Wait for results";
	}
	
}
