package com.cg.service;

import java.util.List;


import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ContestDTO;
import com.cg.dto.ParticipantDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Contest;
import com.cg.entities.Participant;
import com.cg.entities.Quiz;
import com.cg.entities.Score;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.QuizNotApplicableException;
import com.cg.jpautils.ContestUtils;
import com.cg.jpautils.ParticipantUtil;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.ParticipantRepo;

@Service
@Transactional
public class ParticipantService {
	private static final Logger logger = LogManager.getLogger(ParticipantService.class);

	@Autowired
	ParticipantRepo prepo;
	@Autowired 
	ContestService cservice;
	@Autowired
	ScoreService scoreservice;
	
	public List<Participant> getAllParticipants(){
		logger.info("Entered in participant service method getAllParticipants");

		List<Participant> plist=prepo.findAll();
		logger.info("Done in participant service method getAllParticipants");

		return plist;

	}
	
	public Participant createNewParticipant(ParticipantDTO pdto) {
		logger.info("Entered in participant service method createNewParticipants");
		Participant p=ParticipantUtil.getParticipant(pdto);
		logger.info("Done in participant service method createNewParticipants");
		return prepo.save(p);		
	}
	

	public Participant getParticipantById(int pid) throws ParticipantNotFoundException{
		logger.info("Entered in participant service method getParticipantById");
		Optional<Participant> poptional=prepo.findById(pid);
		if(!poptional.isPresent())
			throw new ParticipantNotFoundException("Participant not found for the id");
		logger.info("Done in participant service method getParticipantById");

		return poptional.get();
	}
	
	public String deleteParticipant(int pid) throws ParticipantNotFoundException {
		logger.info("Entered in participant service method deleteParticipant");

		getParticipantById(pid);
		prepo.deleteById(pid);
		logger.info("Done in participant service method deleteParticipant");

		return "Deleted";
	}
	
	public Participant updateContest(int pid, ParticipantDTO pdto) throws ParticipantNotFoundException{
		
		logger.info("Entered in participant service method updateContest");
		Optional<Participant> poptional=prepo.findById(pid);
		if(!poptional.isPresent())
			throw new ParticipantNotFoundException("Cannot perform Update. No participant for given id");
		Participant p=poptional.get();
		p.setContest(pdto.getContest());
		p.setEmail(pdto.getEmail());
		p.setName(pdto.getName());
		prepo.saveAndFlush(p);
		logger.info("Done in participant service method updateContest");
		return p;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contest> getContestWithParticipantID(int pid) throws ParticipantNotFoundException{

		logger.info("Entered in participant service method getContestWithParticipantId");
		Participant p=getParticipantById(pid);
		Set<Contest> cdto= p.getContest();
		logger.info("Done in participant service method getContestWithParticipantId");
		return (List<Contest>) cdto;
	}
	
	public Participant mapParticipantToContest(int pid, int cid) throws ParticipantNotFoundException, ContestNotFoundException{
		logger.info("Entered in participant service method mapParticipantToContest");
		ContestDTO cdto=cservice.getContest(cid);
		Participant p=getParticipantById(pid);
		Contest contest=ContestUtils.convertToContest(cdto);
		
		Set<Contest> cset=p.getContest();
		cset.add(contest);
		p.setContest(cset);
		logger.info("Done in participant service method mapParticipantToContest");
		return prepo.saveAndFlush(p);
				
	}
	
	public QuizDTO startQuiz(int pid, int contestid, int roundNo) throws ParticipantNotFoundException, ContestNotFoundException, QuizNotApplicableException {

		
		//checks if participant is eliminated from current contest
		List<Score> scores=scoreservice.showScoreForContest(contestid);
		for(Score s:scores) {
		logger.info("Entered in participant service method startQuiz");
		
			if(s.getParticipantId().getParticipantId()==pid && s.isEliminated())
					throw new QuizNotApplicableException("This quiz is not applicable for participant "+pid);
		}
		
		//returns the quiz if not eliminated
		getParticipantById(pid);
		ContestDTO contestdto= cservice.getContest(contestid);
		Set<Quiz> quizzes=contestdto.getQuiz();
		Quiz quiz=new Quiz();
		for(Quiz q: quizzes) {
			if(q.getRound()==roundNo)
				quiz=q;
		}
		logger.info("Done in participant service method startQuiz");
		return QuizUtils.convertQuizToQuizDTO(quiz);
	}
	
	
	
	//returns the eliminated Participant

	public Participant getEliminationResult(int pid, int contestid, int roundNo) throws ContestNotFoundException {

			
		List<Score> scorelist=scoreservice.showScoreForContest(contestid);

		logger.info("Entered in participant service method getEliminationResult");
		
		int min;
		int x=0;
		while(scorelist.get(x).isEliminated()) {x++;}
		
		//calculates the minimum and stores the participant scoring minimum
		min=x;
		Participant p=new Participant();
		
		for(Score s:scorelist) {
			if(!s.isEliminated() && s.getMarks()<min) {
				min=s.getMarks();
				p=s.getParticipantId();
				
			}
		}
		
		//sets the participant scoring minimum as eliminated and also setting the rank
		for(Score s:scorelist) {
			if(s.getParticipantId()==p) {
				s.setRank(roundNo);
				s.setEliminated(true);
			}
		}
		logger.info("Done in participant service method getEliminationResult");
		return p;
	}
	
	//shows the message on being eliminated
	public String eliminatedDisplay(int pid, int cid) throws ContestNotFoundException, ParticipantNotFoundException {
		logger.info("Entered in participant service method eliminatedDisplay");
		getParticipantById(pid);
		List<Score> scorelist=scoreservice.showScoreForContest(cid);
		int x=0;
		for(Score s:scorelist) {

			if(s.getParticipantId().getParticipantId()==pid)
				x=s.getRank();
		}
		logger.info("Done in participant service method eliminatedDisplay");
		return "Eliminated at round "+x;
	}
	
	//this method will be shown after everyone has submitted
	//this method will be completed during sprint 2
	public String submitQuiz(int pid, int cid, int round) throws ParticipantNotFoundException, ContestNotFoundException {

		logger.info("Entered in participant service method submitQuiz");
		getParticipantById(pid);
		cservice.getContest(cid);

		getParticipantById(pid);
		cservice.getContest(cid);

		
		Participant p=getEliminationResult(pid, cid, round);
		
		//have to implement the following code
		//if eliminated will redirect to /eliminated
		//else will get redirected to /round++/start
		if(p.getParticipantId()==pid)
			return eliminatedDisplay(pid, cid);
		logger.info("Done in participant service method submitQuiz");
		return "You move to the next round "+ ++round;
	}
	
}
