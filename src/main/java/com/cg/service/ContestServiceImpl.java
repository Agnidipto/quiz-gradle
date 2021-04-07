package com.cg.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ContestDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Contest;
import com.cg.entities.Quiz;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.jpautils.ContestUtils;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.ContestRepo;

@Service
@Transactional
public class ContestServiceImpl implements ContestService {
	
	private static final Logger logger = LogManager.getLogger(ContestServiceImpl.class);
	@Autowired
	private ContestRepo contestRepo;
	
	@Override
	public ContestDTO addContest(ContestDTO contestDTO) {
		logger.info("Entered in Contest service method addContest");
		Contest contest = contestRepo.saveAndFlush(ContestUtils.convertToContest(contestDTO));
		logger.info("Done in Contest service method addContest");
		return ContestUtils.convertToContestDto(contest);
	}
	
	@Override
	public ContestDTO addQuizToContest(Integer contestID, List<QuizDTO> quizzes) throws ContestNotFoundException {
		logger.info("Entered in Contest service method addQuizToContest");
		List<Quiz> quizEntity = QuizUtils.convertQuizDTOListToQuizList(quizzes);
		Optional<Contest> opt = contestRepo.findById(contestID);
		if (!opt.isPresent()) {
			throw new ContestNotFoundException("Contest with that ID is not present in the database");
		}
		Contest contest = opt.get();
		contest.getQuiz().addAll(quizEntity);
		contest.setQuiz_no(contest.getQuiz().size() - 1);
		contest = contestRepo.saveAndFlush(contest);
		logger.info("Done in Contest service method addQuizToContest");
		return ContestUtils.convertToContestDto(contest);
	}
	
	@Override
	public ArrayList<ContestDTO> getContests(){
		logger.info("Entered in Contest service method getContests");
		ArrayList<Contest> contest = (ArrayList<Contest>) contestRepo.findAll();
		ArrayList<ContestDTO> contestDTOList = ContestUtils.convertToContestDtoList(contest);
		logger.info("Done in Contest service method getContests");
		return contestDTOList;
	}
	
	@Override
	public ContestDTO getContest(Integer contestID) throws ContestNotFoundException {
		logger.info("Entered in Contest service method getContest");
		Optional<Contest> optional = contestRepo.findById(contestID);
		if(optional.isPresent()) {
			Contest contest = optional.get();
			logger.info("Entered in Contest service method getContest");
			return ContestUtils.convertToContestDto(contest);
		}
		else {
			throw new ContestNotFoundException("Contest with this id is not available!!!");
		}
	}
	
	@Override
	public boolean deleteContest(Integer contestID) throws ContestNotFoundException {
		logger.info("Entered in Contest service method deleteContest");
		Optional<Contest> deleteContest = contestRepo.findById(contestID);
		Contest contest = null;
		if(deleteContest.isPresent()) {
			
			contest = deleteContest.get();
		}else {
			throw new ContestNotFoundException("Contest with given id not found");
		}
		
		contestRepo.delete(contest);
		logger.info("Done in Contest service method deleteContest");
		Contest contest1 = contestRepo.findById(contestID).get();
		if(null == contest1){
            		return true;
        	}
        	return false;
		}
	
	@Override
	public ContestDTO updateContest(Integer contestID, ContestDTO contestDTO) throws ContestNotFoundException {
		logger.info("Entered in Contest service method updateContest");
		Optional<Contest> updateContest = contestRepo.findById(contestID);
		Contest contest = null;
		if(updateContest.isPresent()) {
			contest = updateContest.get();
			contest.setContest_ID(contestDTO.getContest_ID());
			contest.setName(contestDTO.getName());
			contest.setTotal_participants(contestDTO.getTotal_participants());
			contest.setQuiz_no(contestDTO.getQuiz_no());
			
		}
		else {
			throw new ContestNotFoundException("Contest with given id not found");
		}
		contestRepo.flush();
		logger.info("Done in Contest service method updateContest");
		return ContestUtils.convertToContestDto(contest);
	}
	
}
