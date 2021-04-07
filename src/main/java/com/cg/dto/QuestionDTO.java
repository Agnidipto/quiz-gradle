package com.cg.dto;

import org.springframework.stereotype.Component;

@Component
public class QuestionDTO {
	
	private Integer question_ID;		// declaring the variables
	private String questionStatement;
	private String option_A;
	private String option_B;
	private String option_C;
	private String option_D;
	private String correctAnswer;
	private String explanation;
	
	//Setters and Getters for the variable
	
	public Integer getQuestion_ID() {
		return question_ID;
	}
	public void setQuestion_ID(Integer question_ID) {
		this.question_ID = question_ID;
	}
	public String getQuestionStatement() {
		return questionStatement;
	}
	public void setQuestionStatement(String questionStatement) {
		this.questionStatement = questionStatement;
	}
	public String getOption_A() {
		return option_A;
	}
	public void setOption_A(String option_A) {
		this.option_A = option_A;
	}
	public String getOption_B() {
		return option_B;
	}
	public void setOption_B(String option_B) {
		this.option_B = option_B;
	}
	public String getOption_C() {
		return option_C;
	}
	public void setOption_C(String option_C) {
		this.option_C = option_C;
	}
	public String getOption_D() {
		return option_D;
	}
	public void setOption_D(String option_D) {
		this.option_D = option_D;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionStatement == null) ? 0 : questionStatement.hashCode());
		result = prime * result + ((option_A == null) ? 0 : option_A.hashCode());
		result = prime * result + ((option_B == null) ? 0 : option_B.hashCode());
		result = prime * result + ((option_C == null) ? 0 : option_C.hashCode());
		result = prime * result + ((option_D == null) ? 0 : option_D.hashCode());
		result = prime * result + ((correctAnswer == null) ? 0 : correctAnswer.hashCode());
		result = prime * result + ((explanation == null) ? 0 : explanation.hashCode());	
		result = prime * result + ((question_ID == null) ? 0 : question_ID.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuestionDTO other = (QuestionDTO) obj;
		if (correctAnswer == null) {
			if (other.correctAnswer != null)
				return false;
		} else if (!correctAnswer.equals(other.correctAnswer))
			return false;
		if (explanation == null) {
			if (other.explanation != null)
				return false;
		} else if (!explanation.equals(other.explanation))
			return false;
		if (option_A == null) {
			if (other.option_A != null)
				return false;
		} else if (!option_A.equals(other.option_A))
			return false;
		if (option_B == null) {
			if (other.option_B != null)
				return false;
		} else if (!option_B.equals(other.option_B))
			return false;
		if (option_C == null) {
			if (other.option_C != null)
				return false;
		} else if (!option_C.equals(other.option_C))
			return false;
		if (option_D == null) {
			if (other.option_D != null)
				return false;
		} else if (!option_D.equals(other.option_D))
			return false;
		if (questionStatement == null) {
			if (other.questionStatement != null)
				return false;
		} else if (!questionStatement.equals(other.questionStatement))
			return false;
		if (question_ID == null) {
			if (other.question_ID != null)
				return false;
		} else if (!question_ID.equals(other.question_ID))
			return false;
		
		return true;
	}
	
}
