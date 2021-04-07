package com.cg.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ContestDTO;
import com.cg.dto.ScoreDTO;
import com.cg.entities.Contest;
import com.cg.entities.Participant;
import com.cg.entities.Score;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.ScoreNotFoundException;
import com.cg.jpautils.ContestUtils;
import com.cg.jpautils.ScoreUtil;
import com.cg.repo.ScoreRepo;

@Service
public class ScoreService {
	private static final Logger logger = LogManager.getLogger(ScoreService.class);

	@Autowired
	ScoreRepo srepo;
	
	@Autowired
	ParticipantService pservice;
	
	@Autowired
	ContestServiceImpl cservice;
	

	public List<Score> getAllScores() {
		logger.info("Entered in score service method getAllScores");
		List<Score> scores= srepo.findAll();
		logger.info("Done in score service method getAllScores");

		return scores;
	}
	
	public Score createNewScore(ScoreDTO sdto) {
		logger.info("Entered in score service method createNewScore");
		Score s=ScoreUtil.getScore(sdto);
		logger.info("Done in score service method createNewScore");
		return srepo.saveAndFlush(s);
	}
	
	public String deleteScore(int scoreid) throws ScoreNotFoundException {
		logger.info("Entered in score service method deleteScore");

		Optional<Score> s=srepo.findById(scoreid);
		if(!s.isPresent())
			throw new ScoreNotFoundException("Deletion cannot occur. Score Not Found");
		srepo.deleteById(scoreid);	
		logger.info("Done in score service method deleteScore");

		return "deleted";

	}
	
	public Score getScoreById(int scoreid) throws ScoreNotFoundException {
		logger.info("Entered in score service method getScoreById");
		Optional<Score> soptional=srepo.findById(scoreid);
		if(!soptional.isPresent())
			throw new ScoreNotFoundException("Score Not Found for id");
		

		Score s=soptional.get();
		logger.info("Done in score service method getScoreById");
		return s;

	}
	
	public Score updateScore(int scoreid, ScoreDTO sdto) throws ScoreNotFoundException {
		logger.info("Entered in score service method updateScore");
		Optional<Score> soptional=srepo.findById(scoreid);

		if(!soptional.isPresent())
			throw new ScoreNotFoundException("Updation cannot occur. Score Not Found");
		
		Score s= soptional.get();
		s.setMarks(sdto.getMarks());
		s.setRank(sdto.getRank());
		s.setEliminated(sdto.isEliminated());
		logger.info("Done in score service method updateScore");

		return srepo.save(s);
	}
	
	public Score makeNewScore(ScoreDTO sdto, int contestid, int participantid) throws ParticipantNotFoundException, ContestNotFoundException {
		logger.info("Entered in score service method makeNewScore");

		Participant p=pservice.getParticipantById(participantid);

		ContestDTO cdto=cservice.getContest(contestid);
		Contest c=ContestUtils.convertToContest(cdto);
		
		sdto.setContestId(c);
		sdto.setParticipantId(p);
		
		Score s=ScoreUtil.getScore(sdto);
		logger.info("Done in score service method makeNewScore");
		return srepo.saveAndFlush(s);
	}
	

	public List<Score> showScoreForParticipant(int pid) throws ParticipantNotFoundException {
		logger.info("Entered in score service method showScoreForParticipant");
		pservice.getParticipantById(pid);
		List<Score> scorelist=srepo.findAll();

		
		List<Score> slist=new ArrayList<>();
		
		for(Score sdto:scorelist) {
			if(sdto.getParticipantId().getParticipantId()==pid)
				slist.add(sdto);
		}
		logger.info("Done in score service method showScoreForParticipant");
		return slist;
	}
	

	public List<Score> showScoreForContest(int cid) throws ContestNotFoundException {
	logger.info("Entered in score service method showScoreFoContest");
		ContestDTO cdto=cservice.getContest(cid);
		List<Score> scorelist=getAllScores();
		
		List<Score> slist=new ArrayList<>();
		
		for(Score sdto:scorelist) {
			if(sdto.getContestId().getContest_ID().equals(cdto.getContest_ID()))
				slist.add(sdto);
		}
		logger.info("Done in score service method showScoreForContest");
		return slist;
	}
}
