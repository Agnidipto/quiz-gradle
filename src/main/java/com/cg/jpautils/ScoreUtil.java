package com.cg.jpautils;

import java.util.ArrayList;
import java.util.List;

import com.cg.dto.ScoreDTO;
import com.cg.entities.Score;

public class ScoreUtil {
	
	private ScoreUtil() {}

	public static ScoreDTO getScoreDto(Score s) {
		ScoreDTO sdto=new ScoreDTO();
		sdto.setContestId(s.getContestId());
		sdto.setParticipantId(s.getParticipantId());
		sdto.setScoreId(s.getScoreId());
		sdto.setMarks(s.getMarks());
		sdto.setRank(s.getRank());
		sdto.setEliminated(s.isEliminated());
		
		return sdto;
	}
	
	public static Score getScore(ScoreDTO sdto) {
		Score s=new Score();
		
		s.setContestId(sdto.getContestId());
		s.setParticipantId(sdto.getParticipantId());
		s.setScoreId(sdto.getScoreId());
		s.setRank(sdto.getRank());
		s.setMarks(sdto.getMarks());
		s.setEliminated(sdto.isEliminated());
		
		return s;
	}
	
	public static List<ScoreDTO> getScoreDtoList(List<Score> score) {
		ScoreDTO sdto;
		List<ScoreDTO> sdtolist=new ArrayList<>();
		
		for(Score s:score) {
			sdto=getScoreDto(s);
			sdtolist.add(sdto);
		}
		
		return sdtolist;
	}
	
	public static List<Score> getScoreList(List<ScoreDTO> sdtolist){
		List<Score> slist=new ArrayList<>();
		
		for(ScoreDTO sdto:sdtolist) {
			slist.add(getScore(sdto));
		}
		
		return slist;
	}
}
