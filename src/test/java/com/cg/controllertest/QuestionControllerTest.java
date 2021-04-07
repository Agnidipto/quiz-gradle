package com.cg.controllertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.controllers.QuestionController;
import com.cg.dto.QuestionDTO;
import com.cg.entities.Question;
import com.cg.jpautils.QuestionUtil;
import com.cg.repo.QuestionRepo;
import com.cg.service.QuestionServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//@RunWith(SpringRunner.class)
@WebMvcTest(value = QuestionController.class)
public class QuestionControllerTest 
{
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionServiceImpl questionService;
    
    @Test
    void testAddQuestion() throws Exception
    {
    	String URI = "/question/add";
    	QuestionDTO question1 = new QuestionDTO();
    	question1.setQuestion_ID(1);
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		String jsonInput = this.converttoJson(question1);
		Question question=QuestionUtil.convertToQuestion(question1);
		Mockito.when(questionService.addQuestion(Mockito.any(QuestionDTO.class))).thenReturn(question);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		
    }
   
    
    @Test
    void testGetQuestion() throws Exception
    {
    	 String URI = "/question/getAll";
    	 QuestionDTO question1 = new QuestionDTO();
         question1.setQuestionStatement("1");
 		 question1.setOption_A("a");
 		 question1.setOption_B("b");
 		 question1.setOption_C("c");
 		 question1.setOption_D("d");
 		 question1.setCorrectAnswer("a");
 		 question1.setExplanation("i");
         QuestionDTO question2 = new QuestionDTO();
         question2.setQuestionStatement("2");
 		 question2.setOption_A("e");
 		 question2.setOption_B("f");
 		 question2.setOption_C("g");
 		 question2.setOption_D("h");
 		 question2.setCorrectAnswer("e");
 		 question2.setExplanation("z");

         List<QuestionDTO> qlist = new ArrayList<>();
         qlist.add(question1);
         qlist.add(question2);
         
         String jsonInput = this.converttoJson(qlist);
         List<QuestionDTO> qlist1 = new ArrayList<>();
         Mockito.when(questionService.getQuestion()).thenReturn(qlist1);
         MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
         MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
         String jsonOutput = mockHttpServletResponse.getContentAsString();

         assertThat(jsonInput).isEqualTo(jsonOutput);
         
    }
    
    @Test
    void testGetQuestionId() throws Exception{
        String URI= "/question/getID/{qid}";
        QuestionDTO question1 = new QuestionDTO();
    	question1.setQuestion_ID(1);
        question1.setQuestionStatement("1");
		question1.setOption_A("a");
		question1.setOption_B("b");
		question1.setOption_C("c");
		question1.setOption_D("d");
		question1.setCorrectAnswer("a");
		question1.setExplanation("i");
		String jsonInput = this.converttoJson(question1);

        Mockito.when(questionService.getQuestionById(Mockito.anyInt())).thenReturn(question1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 102).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }
    
    private String converttoJson(Object question1) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(question1);
    }
}
