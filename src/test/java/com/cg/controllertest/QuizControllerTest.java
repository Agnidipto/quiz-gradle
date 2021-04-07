package com.cg.controllertest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.cg.controllers.QuizController;
import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.service.QuizService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuizController.class)
public class QuizControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private QuizService quizService;

	@Test //@PostMapping(value = "quiz")
	public void testQuiz() throws Exception {
		String URI = "/quiz";
		QuizDTO q = new QuizDTO();
		q.setQuizId(1); 
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(1);
		q.setTotalQuestions(1); 
		String jsonInput = this.converttoJson(q);

		Mockito.when(quizService.addQuiz(Mockito.any(QuizDTO.class))).thenReturn(q);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		String jsonOutput = mockHttpServletResponse.getContentAsString();
		assertThat(jsonInput).isEqualTo(jsonOutput);
		Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

	}

	@Test //@GetMapping(value = "quiz/{quiz_id}")
	public void testQuizById() throws Exception {
		String URI = "/quiz/{quiz_id}";
		QuizDTO q = new QuizDTO();
		q.setQuizId(2); 
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);
		String jsonInput = this.converttoJson(q);
		
		
		Mockito.when(quizService.getQuiz(Mockito.any(Integer.class))).thenReturn(q);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 2).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	
	
	@Test //@GetMapping(value = "quiz")
	public void testAllQuiz() throws Exception {
		String URI = "/quiz";
		QuizDTO q = new QuizDTO();
		q.setQuizId(2);
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(2);
		q.setTotalQuestions(5);
		Set<QuestionDTO> questions = new HashSet<>();
		QuestionDTO ques = new QuestionDTO();
		ques.setCorrectAnswer("a");
		ques.setExplanation("ex1");
		ques.setOption_A("a");
		ques.setOption_B("b");
		ques.setOption_C("c");
		ques.setOption_D("d");
		ques.setQuestion_ID(1);
		ques.setQuestionStatement("A");
		questions.add(ques);
		q.setQuestions(questions);
		
		QuizDTO q1 = new QuizDTO();
		q1.setQuizId(3);
		q1.setNegativeMark(true);
		q1.setSkip(false);
		q1.setRound(2);
		q1.setTotalQuestions(3);
		Set<QuestionDTO> questions1 = new HashSet<>();
		QuestionDTO ques1 = new QuestionDTO();
		ques1.setCorrectAnswer("b");
		ques1.setExplanation("ex2");
		ques1.setOption_A("a");
		ques1.setOption_B("b");
		ques1.setOption_C("c");
		ques1.setOption_D("d");
		ques1.setQuestion_ID(1);
		ques1.setQuestionStatement("B");
		questions1.add(ques);
		q1.setQuestions(questions1);
		
		List<QuizDTO> quizDtoList = new ArrayList<>(); 
		quizDtoList.add(q);
		quizDtoList.add(q1); 
		
		String jsonInput = this.converttoJson(quizDtoList);
		
		
		Mockito.when(quizService.getAllQuiz()).thenReturn(quizDtoList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 1).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
	}
	
	private String converttoJson(Object q) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(q);
	}
	
	@Test // @PutMapping(value = "quiz/{quiz_id}")
	public void testQuizUpdate() throws Exception{
		String URI ="/quiz/{quiz_id}";
		QuizDTO q = new QuizDTO();
		q.setQuizId(1); 
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(1);
		q.setTotalQuestions(1);
		
		String jsonInput = this.converttoJson(q);
				
		Mockito.when(quizService.updateQuiz(Mockito.any(),Mockito.any(QuizDTO.class))).thenReturn(q);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,1).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);

	}

	@Test //@DeleteMapping(value = "quiz/{quiz_id}")
	public void testDeleteQuiz() throws Exception{
		String URI="/quiz/{quiz_id}";
		QuizDTO q = new QuizDTO();
		q.setQuizId(1); 
		q.setNegativeMark(true);
		q.setSkip(false);
		q.setRound(1);
		q.setTotalQuestions(1);
		String jsonInput = this.converttoJson(q);

		Mockito.when(quizService.deleteQuiz(Mockito.any())).thenReturn(true);
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI,1).accept(MediaType.APPLICATION_JSON)
				.content(jsonInput).contentType(MediaType.APPLICATION_JSON)).andReturn();
		MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
		Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
		
	}


	

}
