package com.cg.dto;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.cg.entities.Quiz;

@Component
public class ContestDTO {
	
	private Integer contest_ID;                    
	private String name;
	private int total_participants;
	private Set<Quiz> quiz;
	private Integer quiz_no;
	
	
	public Integer getContest_ID() {
		return contest_ID;
	}
	public void setContest_ID(Integer contest_ID) {
		this.contest_ID = contest_ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal_participants() {
		return total_participants;
	}
	public void setTotal_participants(int total_participants) {
		this.total_participants = total_participants;
	}
	public Set<Quiz> getQuiz() {
		return quiz;
	}
	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}
	public Integer getQuiz_no() {
		return quiz_no;
	}
	public void setQuiz_no(Integer quiz_no) {
		this.quiz_no = quiz_no;
	}

	
}
