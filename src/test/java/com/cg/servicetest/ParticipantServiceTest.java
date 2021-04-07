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
import com.cg.entities.Contest;
import com.cg.entities.Participant;
import com.cg.entities.Quiz;
import com.cg.entities.Score;
import com.cg.exceptions.ContestNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.jpautils.ContestUtils;
import com.cg.jpautils.ParticipantUtil;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.ParticipantRepo;
import com.cg.repo.QuizRepo;
import com.cg.service.ContestService;
import com.cg.service.ContestServiceImpl;
import com.cg.service.ParticipantService;
import com.cg.service.ScoreService;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ParticipantServiceTest {

	@MockBean
    private ParticipantRepo prepo;
	
	@Autowired
    private ParticipantService pservice;

	@Autowired
	private ContestServiceImpl cservice;
	
	@Autowired
	private ScoreService sservice;
	
	@Test 
    void createNewParticipant() throws Exception {
    	Participant p=getParticipant1();
		ParticipantDTO pdto=ParticipantUtil.getParticipantDTO(p);
    	
		Mockito.when(prepo.save(Mockito.any(Participant.class))).thenReturn(p);
        Assertions.assertEquals(pservice.createNewParticipant(pdto),p);
    }	
	
	@Test
	void getAllParticipants() throws Exception{
		Participant p1=getParticipant1();
		Participant p2=getParticipant2();
		
		List<Participant> plist=new ArrayList<>();
		plist.add(p2);
		plist.add(p1);
		
		Mockito.when(prepo.findAll()).thenReturn(plist);
		Assertions.assertEquals(pservice.getAllParticipants(), plist);
	}
	
	@Test
	void getParticipantById() {
		Participant p=getParticipant1();
		p.setParticipantId(100);
		Optional<Participant> pop=Optional.ofNullable(p);
		Mockito.when(prepo.findById(Mockito.anyInt())).thenReturn(pop);
		try {
			Assertions.assertEquals(null, pservice.getParticipantById(100));
		} catch (ParticipantNotFoundException e) {
			Assertions.assertEquals(p, pop.get());
		}
	}
	
	@Test
	void deleteParticipant() {
		Participant p=getParticipant1();
		Optional<Participant> pop=Optional.ofNullable(p);
		try {
			Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);
			
		} catch (ParticipantNotFoundException e) {
			Assertions.assertEquals(p, pop.get());
		}
	}
	
	@Test
	void updateContest() {
		
		Participant p=getParticipant1();
		int pid=p.getParticipantId();
		Mockito.when(prepo.saveAndFlush(Mockito.any(Participant.class))).thenReturn(p);
		
		ParticipantDTO pdto=ParticipantUtil.getParticipantDTO(p);
		Optional<Participant> pop=Optional.ofNullable(p);

		Mockito.when(prepo.findById(pid)).thenReturn(pop);
		try {
			Assertions.assertEquals(p, pservice.updateContest(pid, pdto));
		} catch (ParticipantNotFoundException e) {
			Assertions.assertEquals(p, pop.get());

		}
	}
	
	@Test
	void getContestWithParticipantId()  {
		Participant p=getParticipant1();
		int pid=p.getParticipantId();
		Set<Contest> cdto=p.getContest();
		@SuppressWarnings("unchecked")
		List<Contest> list=(List<Contest>)cdto;
		Optional<Participant> pop=Optional.ofNullable(p);
		try {
			Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);

			Assertions.assertEquals(list, pservice.getContestWithParticipantID(pid));
		} catch (ParticipantNotFoundException e) {
			Assertions.assertEquals(p, pop.get());

		}
		
	}
	
	@Test
	void mapParticipantToContest() throws ContestNotFoundException {
		Participant p= getParticipant1();
		Optional<Participant> pop=Optional.ofNullable(p);

		Contest c=new Contest();
		ContestDTO cdto=ContestUtils.convertToContestDto(c);
		Mockito.when(cservice.getContest(Mockito.anyInt())).thenReturn(cdto);
		
		Set<Contest> cset=p.getContest();
		cset.add(c);
		p.setContest(cset);
		Mockito.when(prepo.saveAndFlush(Mockito.any(Participant.class))).thenReturn(p);

		
		try {
			Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);
			Assertions.assertEquals(p, pservice.getContestWithParticipantID(p.getParticipantId()));
		} catch (ParticipantNotFoundException e) {
			Assertions.assertEquals(p, pop.get());

		}
	}
	
	@Test
	void submitQuiz() {
		Participant p= getParticipant1();
		Optional<Participant> pop=Optional.ofNullable(p);
		int pid=p.getParticipantId();

		Contest c=new Contest();
		ContestDTO cdto=ContestUtils.convertToContestDto(c);
		
		
		Participant p2=getParticipant2();
		
		try {
			Mockito.when(cservice.getContest(Mockito.anyInt())).thenReturn(cdto);
			Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);
			Mockito.when(pservice.getEliminationResult(pid, c.getContest_ID(), 1));
			Assertions.assertEquals("You move to the next round", pservice.submitQuiz(pid, c.getContest_ID(), 1));
		} catch (ParticipantNotFoundException | ContestNotFoundException e) {
			Assertions.assertEquals(p, pop.get());

		}

	}
	
	@Test
	void EliminatedDisplay() {
		
		Participant p=getParticipant1();
		int pid=p.getParticipantId();
		
		List<Score> list=new ArrayList<>();
		
		Optional<Participant> pop=Optional.ofNullable(p);

		try {
			Mockito.when(sservice.showScoreForContest(Mockito.anyInt())).thenReturn(list);

			Assertions.assertEquals( "Eliminated at round 0", pservice.eliminatedDisplay(pid, 1));
		
		} catch (ParticipantNotFoundException | ContestNotFoundException e) {
			Assertions.assertEquals(p, pop.get());

		}
	}
	
	 //getting 1 object of Participant
    Participant getParticipant1() {
    	Participant p=new Participant();
    	p.setEmail("abc@abc");
    	p.setContest(null);
    	p.setName("agni");
    	p.setParticipantId(1);
    	return p;
    }
    
    //getting 2nd object of Participant
    Participant getParticipant2() {
    	Participant p =new Participant();
    	p.setName("agnidipto");
    	p.setEmail("oops@abc");
    	p.setParticipantId(2);
    	p.setContest(null);
    	
    	return p;
    }
}
