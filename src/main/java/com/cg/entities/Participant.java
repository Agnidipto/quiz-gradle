package com.cg.entities;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="participant")
public class Participant {

	@Id
	@Column(name="participant_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer participantId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;

	@OneToMany
	@JoinColumn(name="contest")
	private Set<Contest> contest;
	
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
		return "Participant [participant_id=" + participantId + ", name=" + name + ", email=" + email + ", contest="
				+ contest + "]";
	}
	
	
	
}
