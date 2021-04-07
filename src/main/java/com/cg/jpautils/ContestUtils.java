package com.cg.jpautils;

import java.util.ArrayList;
import java.util.List;

import com.cg.dto.ContestDTO;
import com.cg.entities.Contest;

public class ContestUtils {
	
	public static Contest convertToContest(ContestDTO contestDTO) {
		Contest contest = new Contest();
		contest.setContest_ID(contestDTO.getContest_ID());
		contest.setName(contestDTO.getName());
		contest.setTotal_participants(contestDTO.getTotal_participants());
		contest.setQuiz_no(contestDTO.getQuiz_no());
		contest.setQuiz(contestDTO.getQuiz());
		return contest;
	}
	
	public static ContestDTO convertToContestDto(Contest contest) {
		ContestDTO contestDTO = new ContestDTO();
		contestDTO.setContest_ID(contest.getContest_ID());
		contestDTO.setName(contest.getName());
		contestDTO.setTotal_participants(contest.getTotal_participants());
		contestDTO.setQuiz_no(contest.getQuiz_no());
		contestDTO.setQuiz(contest.getQuiz());
		return contestDTO;
	}
	
	public static ArrayList<ContestDTO> convertToContestDtoList(ArrayList<Contest> contest){
		ArrayList<ContestDTO> contestDTO = new ArrayList<ContestDTO>();
		for(Contest con : contest) {
			contestDTO.add(convertToContestDto(con));
		}
		return contestDTO;
	}
	
	public static List<Contest> convertToContestList(List<ContestDTO> contestDTO){
		List<Contest> contest =new ArrayList<Contest>();
		for(ContestDTO cdto: contestDTO) {
			contest.add(convertToContest(cdto));
		}
		return contest;
	}
	
}
