package com.cg.dto;

import java.util.Set;

import com.cg.entities.Contest;
import com.cg.entities.Quiz;

public class CreatorDTO {

	private Integer creator_id;
	private Set<Contest> contest;
	private String email;
	private boolean paid=false;
	private String name;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(Integer creator_id) {
		this.creator_id = creator_id;
	}
	public Set<Contest> getContest() {
		return contest;
	}
	public void setContest(Set<Contest> contest) {
		this.contest = contest;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	
	
}
