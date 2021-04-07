package com.cg.repotest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.entities.Score;
import com.cg.repo.ScoreRepo;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ScoreRepoTest {

	@Autowired
	private ScoreRepo srepo;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void testParticipant() throws Exception {
		Score s = new Score();
		Score saveInDb = testEntityManager.persist(s);
		Score getFromInDb = srepo.findById(s.getScoreId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
	}
	
	//testing getting score by id
    @Test
    void testGetScoreId() throws Exception {
    	Score s=getScore1();
    	Score saveInDb = testEntityManager.persist(s);
		Score getFromInDb = srepo.findById(s.getScoreId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
    }
    
    //testing getting all score
    @Test
    void testGetAllScores() throws Exception{
    	Score a1= getScore1();
    	Score a2=getScore2();
    	
    	testEntityManager.persist(a1);
    	testEntityManager.persist(a2);
    	
    	List<Score> list= srepo.findAll();
    	Assert.assertEquals(2, list.size());
    }
    
    //testing deleting by score
    @Test
    void deleteScoreById() throws Exception {
    	Score p1=getScore1();
    	Score p2= getScore2();
    	
    	Score p3 = testEntityManager.persist(p1);
        testEntityManager.persist(p2);

        //delete one ticket DB
        testEntityManager.remove(p3);

        List<Score> alist = srepo.findAll();
        Assert.assertEquals(1, alist.size());
    }
    
    //testing update score
    @Test
    void UpdateScore() throws Exception {
    	Score a=getScore1();
    	
    	testEntityManager.persist(a);
    	
    	Score ad=srepo.findById(a.getScoreId()).get();
    	ad.setMarks(50);

    	testEntityManager.persist(ad);
    	
    	assertThat(ad.getMarks()).isEqualTo(50);
    }
    
    //getting 1 object of Score
    Score getScore1() {
    	Score s=new Score();
    	s.setEliminated(false);
    	s.setRank(20);
    	s.setMarks(100);
    	return s;
    }
    
    //getting 2nd object of Score
    Score getScore2() {
    	Score s=new Score();
    	s.setEliminated(true);
    	s.setRank(2);
    	s.setMarks(80);
    	return s;
    }
}
