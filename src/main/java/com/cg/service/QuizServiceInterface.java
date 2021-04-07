package com.cg.service;

import java.util.List;
import java.util.Set;

import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.exceptions.QuizNotFoundException;

public interface QuizServiceInterface {

	List<QuizDTO> getAllQuiz();

	QuizDTO getQuiz(Integer quiz_id) throws QuizNotFoundException;

	QuizDTO updateQuiz(Integer quiz_id, QuizDTO quizdto) throws QuizNotFoundException;

	boolean deleteQuiz(Integer quiz_id) throws QuizNotFoundException;

	QuizDTO addQuestionToQuiz(Integer quiz_id, Set<QuestionDTO> questions) throws QuizNotFoundException;

	QuizDTO addQuiz(QuizDTO quizdto);

}
