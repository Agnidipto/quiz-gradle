package com.cg.repotest;

import com.cg.entities.Question;
import com.cg.repo.QuestionRepo;

import org.junit.*;
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

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepoTest
{
	@Autowired
    private  QuestionRepo quesRepo;

    @Autowired
    private TestEntityManager testEntityManager;
    
    @Test
    public void testGetQuestion() throws Exception{
    	Question question1 = new Question();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
        Question question2 = new Question();
        question2.setQuestionStatement("2");
		question2.setOption_A("a");
		question2.setOption_B("b");
		question2.setOption_C("c");
		question2.setOption_D("d");
		question2.setCorrectAnswer("a");
		question2.setExplanation("i");
        //Save into in memory database
        testEntityManager.persist(question1);
        testEntityManager.persist(question2);

        //Retrieve all Questions
        List<Question> qlist = (List<Question>) quesRepo.findAll();

        Assert.assertEquals(2, qlist.size());
    }
    
    @Test
    public void testgetQuestionById() throws Exception{
    	Question question1 = new Question();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");

        //Insert Data into in memory database
		Question saveInDb = testEntityManager.persist(question1);
        //Get Data from DB
		Question getInDb = quesRepo.findById(question1.getQuestion_ID()).get();
        assertThat(getInDb).isEqualTo(saveInDb);
    }
    
    @Test
    public void testNewQuestion() throws Exception{
        Question question = getQuestion();
        Question saveInDb = testEntityManager.persist(question);
        Question getFromInDb = quesRepo.findById(saveInDb.getQuestion_ID()).get();
        assertThat(getFromInDb).isEqualTo(saveInDb);
    }
    
    @Test
    public void testDeleteQuestionById() throws Exception{
    	Question question1 = new Question();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
        Question question2 = new Question();
        question2.setQuestionStatement("2");
		question2.setOption_A("a");
		question2.setOption_B("b");
		question2.setOption_C("c");
		question2.setOption_D("d");
		question2.setCorrectAnswer("a");
		question2.setExplanation("i");

		Question question = testEntityManager.persist(question1);
        testEntityManager.persist(question2);

        testEntityManager.remove(question);

        List<Question> qlist = (List<Question>) quesRepo.findAll();
        Assert.assertEquals(1,qlist.size());

    }
    
    @Test
    public void testUpdateQuestion(){

    	Question question1 = new Question();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");

        testEntityManager.persist(question1);

        Optional<Question> optional = quesRepo.findById(1);
        
        if(optional.isPresent())
		{
			Question getFromDb= optional.get();
			getFromDb.setCorrectAnswer("e");	        
	        testEntityManager.persist(getFromDb);       
	        assertThat(getFromDb.getQuestion_ID()).isEqualTo("1");
		}
        
    }
	private Question getQuestion() 
	{
		Question question1 = new Question();
		question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		return question1;
		
	}
}
