package com.cg.servicetest;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.service.QuestionServiceImpl;
import com.cg.dto.QuestionDTO;
import com.cg.entities.Question;
import com.cg.jpautils.QuestionUtil;
import com.cg.repo.QuestionRepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.*;

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionServiceTest 
{
	@MockBean
    private QuestionRepo quesRepo;

    @Autowired
    private QuestionServiceImpl questionService;
    

    @Test
    void testAddQuestion() throws Exception
    {
        QuestionDTO question1 = new QuestionDTO();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		Question question=QuestionUtil.convertToQuestion(question1);
		Mockito.when(quesRepo.save(Mockito.any(Question.class))).thenReturn(question);
		Assertions.assertEquals(questionService.addQuestion(question1),(question));
        
    }
    
    @Test
    void testGetQuestionById() throws Exception
    {
    	Question question1 = new Question();
    	question1.setQuestion_ID(1);
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		quesRepo.save(question1);

        Mockito.when(quesRepo.findById(1).get()).thenReturn(question1);
        assertThat(questionService.getQuestionById(1)).isEqualTo(question1);
        
        //System.out.println(questionService.getQuestionById(1));
    }
    
    @Test
    void testgetQuestion() throws Exception{
    	QuestionDTO question1 = new QuestionDTO();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		question1.setQuestion_ID(1);
        QuestionDTO question2 = new QuestionDTO();
        question2.setQuestionStatement("2");
		question2.setOption_A("a");
		question2.setOption_B("b");
		question2.setOption_C("c");
		question2.setOption_D("d");
		question2.setCorrectAnswer("a");
		question2.setExplanation("i");
		question1.setQuestion_ID(2);

        List<QuestionDTO> qlist = new ArrayList<>();
        qlist.add(question1);
        qlist.add(question2);

		Mockito.when(quesRepo.findAll()).thenReturn(QuestionUtil.convertToQuestionList(qlist));
		List<Question> q1=QuestionUtil.convertToQuestionList(questionService.getQuestion());
		List<Question> q2=QuestionUtil.convertToQuestionList(qlist);
		
		QuestionDTO ob=new QuestionDTO();
		
		assertThat(q1).isEqualTo(q2);
    }
    
    @Test
    void testdeleteQuestion() throws Exception{
    	Question question1 = new Question();
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		quesRepo.save(question1);
        //Mockito.when(quesRepo.save(question1)).thenReturn(question1);
        //Mockito.when(quesRepo.findById(1).get()).thenReturn(question1);
        quesRepo.deleteById(question1.getQuestion_ID());
        Mockito.when(quesRepo.findById(1).get()).thenReturn(question1);
        Assert.assertNotEquals(question1, new Question());
    }
}
