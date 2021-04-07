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

import com.cg.entities.Contest;
import com.cg.repo.ContestRepo;

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
public class ContestRepoTest {
	
	@Autowired
	private ContestRepo contestRepo;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void testNewContest() throws Exception{
        Contest contest = getContest();
        Contest saveInDb = testEntityManager.persist(contest);
        Contest getFromInDb = contestRepo.findById(saveInDb.getContest_ID()).get();
        assertThat(getFromInDb).isEqualTo(saveInDb);
    }
	
	@Test
	public void testGetContestById() throws Exception{
        Contest contest = new Contest();
        contest.setName("Test1");
        contest.setQuiz_no(10);
        contest.setTotal_participants(11);

        //Insert Data into in memory database
        Contest saveInDb = testEntityManager.persist(contest);
        //Get Data from DB
        Contest getInDb = contestRepo.findById(contest.getContest_ID()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
    }
	
	@Test
	public void testGetAllContests() throws Exception{
        Contest contest1 = new Contest();
        contest1.setName("Contest1");
        contest1.setQuiz_no(12);
        contest1.setTotal_participants(13);

        Contest contest2 = new Contest();
        contest2.setName("Contest2");
        contest2.setQuiz_no(7);
        contest2.setTotal_participants(8);

        //Save into in memory database
        testEntityManager.persist(contest1);
        testEntityManager.persist(contest2);

        //Retrieve all tickets
        List<Contest> contestList = (List<Contest>) contestRepo.findAll();

        Assert.assertEquals(2, contestList.size());
    }
	
	@Test
	public void testDeleteContestById() throws Exception{
        Contest contest1 = new Contest();
        contest1.setName("Test2");
        contest1.setQuiz_no(24);
        contest1.setTotal_participants(25);

        Contest contest2 = new Contest();
        contest2.setName("Test3");
        contest2.setQuiz_no(34);
        contest2.setTotal_participants(35);

        Contest contest = testEntityManager.persist(contest1);
        testEntityManager.persist(contest2);

        //delete one ticket DB
        testEntityManager.remove(contest);

        List<Contest> contests = (List<Contest>) contestRepo.findAll();
        Assert.assertEquals(contests.size(), 1);

    }
	
	@Test
	public void testUpdateContest(){

        Contest contest2 = new Contest();
        contest2.setName("Test2");
        contest2.setQuiz_no(38);
        contest2.setTotal_participants(39);

        testEntityManager.persist(contest2);

        Contest getFromDb = contestRepo.findById(contest2.getContest_ID()).get();
        getFromDb.setName("Test3");
        testEntityManager.persist(getFromDb);

        assertThat(getFromDb.getName()).isEqualTo("Test3");
    }
	
	private Contest getContest() {
		Contest contest = new Contest();
		contest.setName("Test");
		contest.setQuiz_no(7);
		contest.setTotal_participants(8);
		return contest;
	}
	
}
