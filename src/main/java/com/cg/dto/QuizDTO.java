package com.cg.dto;

import java.util.Set;

import com.cg.entities.Question;

public class QuizDTO {

	private Integer quizId;
	private Integer totalQuestions;
	private Boolean negativeMark;
	private Boolean skip;
	private Integer round;
	private Set<QuestionDTO> questions;
	private Integer time;
	private String quizName;
	
	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	// getter and setter methods
	public Integer getQuizId() {
		return quizId;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQues) {
		this.totalQuestions = totalQues;
	}

	public Boolean getNegativeMark() {
		return negativeMark;
	}

	public void setNegativeMark(Boolean negativeMark) {
		this.negativeMark = negativeMark;
	}

	public Boolean getSkip() {
		return skip;
	}

	public void setSkip(Boolean skip) {
		this.skip = skip;
	}

	public Set<QuestionDTO> getQuestions() {
		return questions;

	}

	public void setQuestions(Set<QuestionDTO> questions) {
		this.questions = questions;
	}

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	// over-riding the hashCode 'cause of testing assertEquals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((negativeMark == null) ? 0 : negativeMark.hashCode());
		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
		result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
		result = prime * result + ((round == null) ? 0 : round.hashCode());
		result = prime * result + ((skip == null) ? 0 : skip.hashCode());
		result = prime * result + ((totalQuestions == null) ? 0 : totalQuestions.hashCode());
		return result;
	}

	// over-riding the equals because of testing assertEquals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuizDTO other = (QuizDTO) obj;
		if (negativeMark == null) {
			if (other.negativeMark != null)
				return false;
		} else if (!negativeMark.equals(other.negativeMark))
			return false;
		if (questions == null) {
			if (other.questions != null)
				return false;
		} else if (!questions.equals(other.questions))
			return false;
		if (quizId == null) {
			if (other.quizId != null)
				return false;
		} else if (!quizId.equals(other.quizId))
			return false;
		if (round == null) {
			if (other.round != null)
				return false;
		} else if (!round.equals(other.round))
			return false;
		if (skip == null) {
			if (other.skip != null)
				return false;
		} else if (!skip.equals(other.skip))
			return false;
		if (totalQuestions == null) {
			if (other.totalQuestions != null)
				return false;
		} else if (!totalQuestions.equals(other.totalQuestions))
			return false;
		return true;
	}

}
