package com.cg.jpautils;

import com.cg.dto.QuestionDTO;
import com.cg.entities.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class QuestionUtil 
{
	//code to get list of questiondto
	public static List<QuestionDTO> convertToQuestionDtoList(List<Question> list)
	{
		List<QuestionDTO> dtolist = new ArrayList<QuestionDTO>();
		for(Question question : list) 
			dtolist.add(convertToQuestionDTO(question));
		return dtolist;
	}
	
	public static List<Question> convertToQuestionList(List<QuestionDTO> qlist)
	{
		List<Question> questions = new ArrayList<Question>();
		for(QuestionDTO question : qlist) 
			questions.add(convertToQuestion(question));
		return questions;
	}
	
	//Code to get as set
	public static Set<QuestionDTO> convertToQuestionDtoSet(Set<Question> set)
	{
		Set<QuestionDTO> dtoSet = new HashSet<QuestionDTO>();
		for(Question question : set) 
			dtoSet.add(convertToQuestionDTO(question));
		return dtoSet;
	}
	
	public static Set<Question> convertToQuestionSet(Set<QuestionDTO> set)
	{
		Set<Question> questions = new HashSet<Question>();
		for(QuestionDTO question : set) 
			questions.add(convertToQuestion(question));
		return questions;
	}
	
	//code to convert questiondto to question
	public static Question convertToQuestion(QuestionDTO questiondto) 
	{
		Question question = new Question();
		question.setQuestion_ID(questiondto.getQuestion_ID());
		question.setQuestionStatement(questiondto.getQuestionStatement());
		question.setOption_A(questiondto.getOption_A());
		question.setOption_B(questiondto.getOption_B());
		question.setOption_C(questiondto.getOption_C());
		question.setOption_D(questiondto.getOption_D());
		question.setCorrectAnswer(questiondto.getCorrectAnswer());
		question.setExplanation(questiondto.getExplanation());
		return question;
	}
	
	//code to convert question to questiondto
	public static QuestionDTO convertToQuestionDTO(Question question)
	{
		QuestionDTO questiondto = new QuestionDTO();
		questiondto.setQuestion_ID(question.getQuestion_ID());
		questiondto.setQuestionStatement(question.getQuestionStatement());
		questiondto.setOption_A(question.getOption_A());
		questiondto.setOption_B(question.getOption_B());
		questiondto.setOption_C(question.getOption_C());
		questiondto.setOption_D(question.getOption_D());
		questiondto.setCorrectAnswer(question.getCorrectAnswer());
		questiondto.setExplanation(question.getExplanation());
		return questiondto;
	}
	
}
