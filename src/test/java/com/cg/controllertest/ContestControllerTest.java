package com.cg.controllertest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.controllers.ContestController;
import com.cg.dto.ContestDTO;
import com.cg.entities.Contest;
import com.cg.service.ContestService;
import com.cg.service.ContestServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value=ContestController.class)
public class ContestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ContestServiceImpl contestService;
	
	@Test
	public void testNewContest() throws Exception{
        String URI = "/creator/contest";
        
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setContest_ID(1);
        contestDTO.setName("Test1");
        contestDTO.setQuiz_no(400);
        contestDTO.setTotal_participants(401);
        String jsonInput = this.converttoJson(contestDTO);

        Mockito.when(contestService.addContest(Mockito.any(ContestDTO.class))).thenReturn(contestDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();
        assertThat(jsonInput).isEqualTo(jsonOutput);
        Assertions.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }
	
	@Test
	public void testGetTicketById() throws Exception{
        String URI= "/creator/contest/{contestID}";
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setContest_ID(1);
        contestDTO.setName("Test1");
        contestDTO.setQuiz_no(400);
        contestDTO.setTotal_participants(401);
        String jsonInput = this.converttoJson(contestDTO);

        Mockito.when(contestService.getContest(Mockito.any())).thenReturn(contestDTO);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI, 1).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }
//	
	@Test
	public void testGetAllContests() throws Exception{
        String URI = "/creator/contest";
        ContestDTO contestDTO = new ContestDTO();
        contestDTO.setContest_ID(1);
        contestDTO.setName("Test1");
        contestDTO.setQuiz_no(400);
        contestDTO.setTotal_participants(401);

        ContestDTO contestDTO1 = new ContestDTO();
        contestDTO1.setContest_ID(2);
        contestDTO1.setName("Test2");
        contestDTO1.setQuiz_no(401);
        contestDTO1.setTotal_participants(402);

        ArrayList<ContestDTO> contestDTOList = new ArrayList<>();
        contestDTOList.add(contestDTO);
        contestDTOList.add(contestDTO1);

        String jsonInput = this.converttoJson(contestDTOList);

        Mockito.when(contestService.getContests()).thenReturn(contestDTOList);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }
//	
	@Test
	public void testDeleteContestById() throws Exception{
        String URI = "/creator/contest/{contestID}";
        ContestDTO contestDTO1 = new ContestDTO();
        contestDTO1.setContest_ID(2);
        contestDTO1.setName("Test2");
        contestDTO1.setQuiz_no(401);
        contestDTO1.setTotal_participants(402);

        Mockito.when(contestService.getContest(Mockito.any())).thenReturn(contestDTO1);
        Mockito.when(contestService.deleteContest(Mockito.any())).thenReturn(true);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete(URI, 2).accept(MediaType.
        		APPLICATION_JSON)).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

    }
//	
	@Test
	public void testUpdateContest() throws Exception{

        String URI = "/creator/contest/{contestID}";
        ContestDTO contestDTO1 = new ContestDTO();
        contestDTO1.setContest_ID(2);
        contestDTO1.setName("Test2");
        contestDTO1.setQuiz_no(401);
        contestDTO1.setTotal_participants(402);
        
        ContestDTO contestDTO2 = new ContestDTO();
        contestDTO2.setContest_ID(3);
        contestDTO2.setName("Test3");
        contestDTO2.setQuiz_no(403);
        contestDTO2.setTotal_participants(404);
        
        String jsonInput = this.converttoJson(contestDTO1);

        Mockito.when(contestService.updateContest(Mockito.any(), contestDTO1)).thenReturn(contestDTO1);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.put(URI,contestDTO2,2).accept(MediaType.APPLICATION_JSON).content(jsonInput).contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        String jsonOutput = mockHttpServletResponse.getContentAsString();

        assertThat(jsonInput).isEqualTo(jsonOutput);
    }
	
	/**
     * Convert Object into Json String by using Jackson ObjectMapper
     * @param ticket
     * @return
     * @throws JsonProcessingException
     */
    private String converttoJson(Object contest) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(contest);
    }
	
}
