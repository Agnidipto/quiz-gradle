package com.cg.dto;

import com.cg.entities.Contest;
import com.cg.entities.Participant;

public class ScoreDTO {

	private Integer scoreId;
	private Contest contestId;
	private Participant participantId;
	private Integer marks=0;
	private Integer rank;
	private boolean eliminated=false;
	
	public boolean isEliminated() {
		return eliminated;

	}
	public void setEliminated(boolean eliminated) {
		this.eliminated = eliminated;
	}
	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}

	public Contest getContestId() {
		return contestId;
	}
	public void setContestId(Contest contestId) {
		this.contestId = contestId;
	}
	public Participant getParticipantId() {
		return participantId;
	}
	public void setParticipantId(Participant participantId) {
		this.participantId = participantId;
	}
	public Integer getMarks() {
		return marks;
	}
	public void setMarks(Integer marks) {
		this.marks = marks;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "ScoreDTO [score_id=" + scoreId + ", contest_id=" + contestId + ", participant_id=" + participantId
				+ ", score=" + marks + ", rank=" + rank + "]";
	}

	
}
