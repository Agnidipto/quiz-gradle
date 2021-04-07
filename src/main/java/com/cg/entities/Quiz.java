package com.cg.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quiz")
public class Quiz {

	@Id
	@Column(name = "quiz_id") // primary key
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer quizId;

	@Column(name = "skip")
	private boolean skip = false;

	@Column(name = "total_questions")
	private Integer totalQuestions;

	@Column(name = "negative_mark")
	private boolean negativeMark = false;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "questions")
	private Set<Question> questions;

	@Column(name = "round")
	private Integer round;
	
	@Column(name="time")
	private Integer time;
	
	@Column(name="quiz_name")
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

	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public boolean isNegativeMark() {
		return negativeMark;
	}

	public void setNegativeMark(boolean negativeMark) {
		this.negativeMark = negativeMark;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Integer getRound() {
		return round;
	}

	public void setRound(Integer round) {
		this.round = round;
	}

	// over-riding the hashCode 'cause of testing assertEquals
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (negativeMark ? 1231 : 1237);
//		result = prime * result + ((questions == null) ? 0 : questions.hashCode());
//		result = prime * result + ((quizId == null) ? 0 : quizId.hashCode());
//		result = prime * result + ((round == null) ? 0 : round.hashCode());
//		result = prime * result + (skip ? 1231 : 1237);
//		result = prime * result + ((totalQuestions == null) ? 0 : totalQuestions.hashCode());
//		return result;
//	}
//
//	// over-riding the equals because of testing assertEquals
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Quiz other = (Quiz) obj;
//		if (negativeMark != other.negativeMark)
//			return false;
//		if (questions == null) {
//			if (other.questions != null)
//				return false;
//		} else if (!questions.equals(other.questions))
//			return false;
//		if (quizId == null) {
//			if (other.quizId != null)
//				return false;
//		} else if (!quizId.equals(other.quizId))
//			return false;
//		if (round == null) {
//			if (other.round != null)
//				return false;
//		} else if (!round.equals(other.round))
//			return false;
//		if (skip != other.skip)
//			return false;
//		if (totalQuestions == null) {
//			if (other.totalQuestions != null)
//				return false;
//		} else if (!totalQuestions.equals(other.totalQuestions))
//			return false;
//		return true;
//	}

	// toString method
	@Override
	public String toString() {
		return "Quiz [quizId=" + quizId + ", round=" + round + ", totalQuestions=" + totalQuestions + ", negative_mark="
				+ negativeMark + ", skip=" + skip + ", Questions=" + questions + "]";
	}

}
