package com.cg.dto;

import java.util.Set;

import com.cg.entities.Contest;

public class ParticipantDTO {

	private Integer participantId;
	
	private String name;
	private String email;
	private Set<Contest> contest;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Contest> getContest() {
		return contest;
	}
	public void setContest(Set<Contest> contest) {
		this.contest = contest;
	}

	@Override
	public String toString() {
		return "ParticipantDTO [participantId=" + participantId + ", name=" + name + ", email=" + email + ", contest="
				+ contest + "]";
	}
	
}
