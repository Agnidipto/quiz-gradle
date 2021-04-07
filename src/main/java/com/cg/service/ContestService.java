package com.cg.service;

import java.util.ArrayList;


import java.util.List;

import com.cg.dto.ContestDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Contest;
import com.cg.exceptions.ContestNotFoundException;



public interface ContestService {
	
	ContestDTO addContest(ContestDTO contestDTO);
	ContestDTO addQuizToContest(Integer contestID, List<QuizDTO> quizzes) throws ContestNotFoundException;
	ArrayList<ContestDTO>getContests();
	ContestDTO getContest(Integer contestID) throws ContestNotFoundException;
	boolean deleteContest(Integer contestID) throws ContestNotFoundException;
	ContestDTO updateContest(Integer contestID, ContestDTO contestDTO) throws ContestNotFoundException;
	
}

