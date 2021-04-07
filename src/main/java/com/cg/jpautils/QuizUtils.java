package com.cg.jpautils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Question;
import com.cg.entities.Quiz;

import com.cg.jpautils.QuestionUtil;

public class QuizUtils {

	// dto -> entity
	public static Quiz convertQuizDTOToQuiz(QuizDTO quizdto) {
		Quiz q = new Quiz();
		q.setQuizId(quizdto.getQuizId());
		q.setTotalQuestions(quizdto.getTotalQuestions());
		q.setNegativeMark(quizdto.getNegativeMark());
		q.setSkip(quizdto.getSkip());
		q.setRound(quizdto.getRound());
		q.setTime(quizdto.getTime());
		q.setQuizName(quizdto.getQuizName());
		if(quizdto.getQuestions()!=null) {
			Set<Question> questions = QuestionUtil.convertToQuestionSet(quizdto.getQuestions());
			q.setQuestions(questions);
		}	
		return q;
	}

	// entity -> dto
	public static QuizDTO convertQuizToQuizDTO(Quiz q) {
		QuizDTO quizdto = new QuizDTO();
		quizdto.setQuizId(q.getQuizId());
		quizdto.setTotalQuestions(q.getTotalQuestions());
		quizdto.setNegativeMark(q.isNegativeMark());
		quizdto.setSkip(q.isSkip());
		quizdto.setRound(q.getRound());
		quizdto.setTime(q.getTime());
		quizdto.setQuizName(q.getQuizName());
		if (q.getQuestions()!=null) {
			Set<QuestionDTO> questionsdto = QuestionUtil.convertToQuestionDtoSet(q.getQuestions());
			quizdto.setQuestions(questionsdto);
		}
		return quizdto;
	}

	// // list -> listDto
	public static List<QuizDTO> convertQuizListToQuizDTOList(List<Quiz> quizList) {
		if(quizList==null) 
			return null;
		List<QuizDTO> quizdtolist = new ArrayList<>();
		for (Quiz q : quizList)
			quizdtolist.add(convertQuizToQuizDTO(q));
		return quizdtolist;
	}

	// set -> dtoSet
	public static Set<QuizDTO> convertQuizSetToQuizDTOSet(List<Quiz> quizSet) {
		if(quizSet==null)
			return null;
		Set<QuizDTO> quizDTOSet = new HashSet<>();
		for (Quiz q : quizSet)
			quizDTOSet.add(convertQuizToQuizDTO(q));
		return quizDTOSet;
	}

	// dtoList -> list
	public static List<Quiz> convertQuizDTOListToQuizList(List<QuizDTO> quizDTOList) {
		if(quizDTOList ==null)
			return null;
		List<Quiz> quizlist = new ArrayList<>();
		for (QuizDTO q : quizDTOList)
			quizlist.add(convertQuizDTOToQuiz(q));
		return quizlist;
	}

	// dtoSet -> set
	public static Set<Quiz> convertQuizDTOSetToQuizSet(Set<QuizDTO> quizDTOset) {
		if(quizDTOset==null)
			return null;
		Set<Quiz> quizlist = new HashSet<>();
		for (QuizDTO q : quizDTOset)
			quizlist.add(convertQuizDTOToQuiz(q));
		return quizlist;
	}
}
