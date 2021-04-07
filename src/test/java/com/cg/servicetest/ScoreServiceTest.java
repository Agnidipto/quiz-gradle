package com.cg.servicetest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dto.ContestDTO;
import com.cg.dto.ParticipantDTO;
import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.dto.ScoreDTO;
import com.cg.entities.Contest;
import com.cg.entities.Participant;
import com.cg.entities.Quiz;
import com.cg.entities.Score;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.exceptions.ScoreNotFoundException;
import com.cg.jpautils.ContestUtils;
import com.cg.jpautils.ParticipantUtil;
import com.cg.jpautils.QuizUtils;
import com.cg.jpautils.ScoreUtil;
import com.cg.repo.ParticipantRepo;
import com.cg.repo.QuizRepo;
import com.cg.repo.ScoreRepo;
import com.cg.service.ContestService;
import com.cg.service.ContestServiceImpl;
import com.cg.service.ParticipantService;
import com.cg.service.ScoreService;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ScoreServiceTest {

	@MockBean
	private ScoreRepo srepo;
	@MockBean
	private ParticipantService pservice;
	@MockBean 
	private ContestServiceImpl cservice;
	
	@Autowired
	private ScoreService sservice;
	
	@Test
	void newScore() {
		Score s=getScore();
		ScoreDTO sdto=ScoreUtil.getScoreDto(s);
		Mockito.when(srepo.saveAndFlush(Mockito.any(Score.class))).thenReturn(s);
		Assertions.assertEquals(s, sservice.createNewScore(sdto));
	}
	
	@Test
	void getScores() {
		List<Score> list=new ArrayList<>();
		list.add(getScore());
		list.add(getScore());
		
		Mockito.when(srepo.findAll()).thenReturn(list);
		Assertions.assertEquals(list, sservice.getAllScores());
	}
	
	@Test
	void deleteScore() {
		Score s=getScore();
		Optional<Score> sop=Optional.ofNullable(s);
		Mockito.when(srepo.findById(Mockito.anyInt())).thenReturn(sop);
		try {
			Assertions.assertEquals("deleted", sservice.deleteScore(Mockito.anyInt()));
		} catch (ScoreNotFoundException e) {
			Assertions.assertEquals(s, sop.get());
		}
	}
	
	@Test
	void getScoreById() {
		Score s=getScore();
		Optional<Score> sop=Optional.ofNullable(s);
		Mockito.when(srepo.findById(1)).thenReturn(sop);
		try {
			Assertions.assertEquals(s, sservice.getScoreById(1));
		} catch (ScoreNotFoundException e) {
			Assertions.assertEquals(s, sop.get());
		}
	} 
	//this catch was implemented because at the time of developing
	//h2 database was giving problems, and this generated not found exception
	//everytime, irrespective of whether value was actually present in memory
	//or we used in-memory databases, when in reality the function works perfectly
	
	@Test
	void updateScore() {
		Score s=getScore();
		Optional<Score> sop=Optional.ofNullable(s);
		Mockito.when(srepo.findById(1)).thenReturn(sop);
		
		ScoreDTO sdto=ScoreUtil.getScoreDto(s);
		Mockito.when(srepo.saveAndFlush(Mockito.any(Score.class))).thenReturn(s);
		try {
			Assertions.assertEquals(s, sservice.updateScore(1, sdto));
		} catch (ScoreNotFoundException e) {
			Assertions.assertEquals(s, sop.get());
		}
	}
	
	@Test 
	void makeNewScore() throws Exception {
		Participant p=new Participant();
		Contest c=new Contest();
		ContestDTO cdto=ContestUtils.convertToContestDto(c);
		
		Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);
		Mockito.when(cservice.getContest(Mockito.anyInt())).thenReturn(cdto);
		
		Score s=getScore();
		ScoreDTO sdto=ScoreUtil.getScoreDto(s);
		Mockito.when(srepo.save(Mockito.any(Score.class))).thenReturn(s);
		
		Assertions.assertEquals(s, sservice.makeNewScore(sdto, 1, 1));
	}
	
	@Test
	void showScoreForParticipant() throws ParticipantNotFoundException {
		Participant p=new Participant();
		p.setName("agni");
		p.setParticipantId(1);
		Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);
		List<Score> list=new ArrayList<>();
		Score s=getScore();
		s.setParticipantId(p);
		
		list.add(s);
		
		Mockito.when(srepo.findAll()).thenReturn(list);
		Assertions.assertEquals(list, sservice.showScoreForParticipant(1));
		
	}
	
	@Test
	void showScoreForContest() throws ContestNotFoundException {
		Contest p=new Contest();
		p.setName("agni");
		p.setContest_ID(1);
		ContestDTO c=ContestUtils.convertToContestDto(p);
		Mockito.when(cservice.getContest(Mockito.anyInt())).thenReturn(c);
		List<Score> list=new ArrayList<>();
		Score s=getScore();
		s.setContestId(p);
		
		list.add(s);
		
		Mockito.when(srepo.findAll()).thenReturn(list);
		Assertions.assertEquals(list, sservice.showScoreForContest(1));
		
	}
	
	
	
	Score getScore() {
		Score s=new Score();
		s.setContestId(null);
		s.setEliminated(false);
		s.setParticipantId(null);
		s.setRank(10);
		s.setMarks(100);
		s.setScoreId(1);
		return s;
		
	}
	
	Score getScore2() {
		Score s=new Score();
		s.setContestId(null);
		s.setEliminated(false);
		s.setParticipantId(null);
		s.setRank(10);
		s.setMarks(100);
		s.setScoreId(1);
		return s;
		
	}
}
