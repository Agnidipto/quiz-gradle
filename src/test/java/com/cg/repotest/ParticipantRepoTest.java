package com.cg.repotest;

import com.cg.entities.Participant;
import com.cg.repo.ParticipantRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.*;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ParticipantRepoTest {

	@Autowired
    private  ParticipantRepo prepo;

	//h2 database
    @Autowired
    private TestEntityManager testEntityManager;
    
    //testing saving into database
    @Test
	void testNewParticipant() throws Exception {
		Participant p = getParticipant1();
		Participant saveInDb = testEntityManager.persist(p);
		Participant getFromInDb = prepo.findById(p.getParticipantId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
	}
    
    //testing getting participant by id
    @Test
    void testGetParticipantById() throws Exception {
    	Participant p=getParticipant1();
    	Participant saveInDb = testEntityManager.persist(p);
		Participant getFromInDb = prepo.findById(p.getParticipantId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
    }
    
    //testing getting all participant
    @Test
    void testGetAllParticipants() throws Exception{
    	Participant p1= getParticipant1();
    	Participant p2=getParticipant2();
    	
    	testEntityManager.persist(p1);
    	testEntityManager.persist(p2);
    	
    	List<Participant> partlist= prepo.findAll();
    	Assert.assertEquals(2, partlist.size());
    }
    
    //testing deleting by participant
    @Test
    void deleteParticipantById() throws Exception {
    	Participant p1=getParticipant1();
    	Participant p2= getParticipant2();
    	
    	Participant p3 = testEntityManager.persist(p1);
        testEntityManager.persist(p2);

        //delete one ticket DB
        testEntityManager.remove(p3);

        List<Participant> plist = prepo.findAll();
        Assert.assertEquals(1, plist.size());
    }
    
    //testing update participant
    @Test
    void UpdateParticipant() throws Exception {
    	Participant p=getParticipant1();
    	
    	testEntityManager.persist(p);
    	
    	Participant part=prepo.findById(p.getParticipantId()).get();
    	part.setName("Dew");

    	testEntityManager.persist(part);
    	
    	assertThat(part.getName()).isEqualTo("Dew");
    }
    
    //getting 1 object of Participant
    Participant getParticipant1() {
    	Participant p=new Participant();
    	p.setEmail("abc@abc");
    	p.setContest(null);
    	p.setName("agni");
    	return p;
    }
    
    //getting 2nd object of Participant
    Participant getParticipant2() {
    	Participant p =new Participant();
    	p.setName("agnidipto");
    	p.setEmail("oops@abc");
    	p.setContest(null);
    	
    	return p;
    }
}
