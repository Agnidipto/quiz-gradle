package com.cg.entities;

import java.util.Set;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity					//creating the entity for contest
@Table(name="contest")  // creating table for contest with table name contest
public class Contest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //id is generated automatically using this strategy
	@Column(name="Contest_ID ", nullable = false)   //column name for contest_ID is set to Contest_id in table
	private Integer contest_ID;                    //here contest_ID is the primary key in the contest table
	
	@Column(name="Name")
	private String name;
	
	@Column(name="Total_Participants")
	private int total_participants;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name="con_fid", referencedColumnName = "contest_ID")
	@JoinColumn(name="quiz")
	@Column(name="Quiz_id")
	private Set<Quiz> quiz; //Initialization required to avoid NullPointerException
	
	@Column(name="Quiz_No")
	private Integer quiz_no;
	
	//setters and getters for all the attributes

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

	//toString method
	
	@Override
	public String toString() {
		return "Contest [contest_ID=" + contest_ID + ", name=" + name + ", total_participants=" + total_participants
				+ ", quiz=" + quiz + ", quiz_no=" + quiz_no + "]";
	}
	
}
