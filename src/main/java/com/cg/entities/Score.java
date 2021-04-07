package com.cg.entities;
import javax.persistence.*;

@Entity
@Table(name="Score")
public class Score {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="score_id")
	private Integer scoreId;
	
	@OneToOne
	@JoinColumn(name="contest_id")
	private Contest contestId;
	
	@OneToOne
	@JoinColumn(name="participant_id")

	private Participant participantId;

	@Column(name="marks")
	private Integer marks=0;

	
	@Column(name="rank")
	private Integer rank;
	
	@Column(name="eliminated")
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
		return "Score [score_id=" + scoreId + ", contest_id=" + contestId + ", participant_id=" + participantId
				+ ", score=" + marks + ", rank=" + rank + "]";
	}
	
	

	
	
}
