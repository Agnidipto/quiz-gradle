package com.cg.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dto.ContestDTO;
import com.cg.entities.Contest;
import com.cg.jpautils.ContestUtils;
import com.cg.repo.ContestRepo;
import com.cg.service.ContestService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ContestServiceTest {

	@MockBean
	private ContestRepo contestRepo;
	
	@Autowired
	private ContestService contestService;
	
	@Test
	public void testAddContest() {
		ContestDTO contestDTO = new ContestDTO();
		contestDTO.setContest_ID(1);
		contestDTO.setName("Test1");
		contestDTO.setQuiz_no(499);
		contestDTO.setTotal_participants(500);
		
		Contest contest = ContestUtils.convertToContest(contestDTO);
		
		System.out.println("contest is" + contest);
		System.out.println("contest DTO" + ContestUtils.convertToContestDto(contest));
		System.out.println("contestDTO is" + contestDTO.getName());

        Mockito.when(contestRepo.save(Mockito.any(Contest.class))).thenReturn(contest);
        Assertions.assertEquals(contestService.addContest(contestDTO),contest);
	}
	
	@Test
	public void testGetAllContests() throws Exception{
        ContestDTO contestDTO1 = new ContestDTO();
        contestDTO1.setName("Test1");
        contestDTO1.setQuiz_no(500);
        contestDTO1.setTotal_participants(501);

        ContestDTO contestDTO2 = new ContestDTO();
        contestDTO2.setName("Test2");
        contestDTO2.setQuiz_no(400);
        contestDTO2.setTotal_participants(401);

        ArrayList<ContestDTO> contestDTOList = new ArrayList<>();
        contestDTOList.add(contestDTO1);
        contestDTOList.add(contestDTO2);
        
        

        Mockito.when(contestRepo.findAll()).thenReturn(ContestUtils.convertToContestList(contestDTOList));
        Assertions.assertEquals(contestService.getContests(),contestDTOList);
    }
//	
	@Test
	public void testDeleteContestById() throws Exception{
        Contest contest = new Contest();
        contest.setContest_ID(1);
        contest.setName("Test1");
        contest.setQuiz_no(400);
        contest.setTotal_participants(401);

        Mockito.when(contestRepo.save(contest)).thenReturn(contest);
        Mockito.when(contestRepo.findById(1).get()).thenReturn(contest);
        contestRepo.deleteById(contest.getContest_ID());
        Assertions.assertEquals(contestService.deleteContest(1), true);
    }
//	
	@Test
	public void testDeleteContestByNull() throws Exception{
        Contest contest = new Contest();
        contest.setContest_ID(1);
        Contest nullContest = null;
        Mockito.when(contestRepo.findById(1).get()).thenReturn(nullContest);
        contestRepo.deleteById(contest.getContest_ID());
        Assertions.assertEquals(contestService.deleteContest(1), true);
    }
//	
	@Test
	public void testUpdateContest() throws Exception{

		ContestDTO contestDTO = new ContestDTO();
		contestDTO.setContest_ID(1);
		contestDTO.setName("Test2");
		contestDTO.setQuiz_no(400);
		contestDTO.setTotal_participants(401);

        Mockito.when(contestRepo.findById(1).get()).thenReturn(ContestUtils.convertToContest(contestDTO));
        contestDTO.setName("Test1");
        Mockito.when(contestRepo.save(ContestUtils.convertToContest(contestDTO))).thenReturn(ContestUtils.convertToContest(contestDTO));
        System.out.println(contestDTO.getName());
        assertThat(contestService.updateContest(1, contestDTO)).isEqualTo(contestDTO);
    }
	
}
