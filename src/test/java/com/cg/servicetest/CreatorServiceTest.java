package com.cg.servicetest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dto.*;
import com.cg.entities.*;
import com.cg.jpautils.*;
import com.cg.repo.*;
import com.cg.service.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CreatorServiceTest {

	@MockBean
	private CreatorRepo repo;
	@Autowired
	private CreatorService service;
	
	
	@Test
	public void testAddCreator() throws Exception{
		Creator c1 = getCreator1();
		CreatorDTO c2 = CreatorUtil.convertToCreatorDTO(c1);
		Mockito.when(repo.save(Mockito.any(Creator.class))).thenReturn(c1);
		Assertions.assertEquals(service.addCreator(c2),(c1));
		
	}
	
	@Test
	public void testGetCreators() throws Exception{
		Creator c1 = getCreator1();
		Creator c2 = getCreator2();
		List <Creator> l1 = new ArrayList<>();
		l1.add(c2);
		l1.add(c1);
		Mockito.when(repo.findAll()).thenReturn(l1);
		Assertions.assertEquals(service.getCreators(),CreatorUtil.convertToCreatorDtoList(l1));
		
	}
	
	@Test
	public void testGetCreator() throws Exception{
		Creator c1 = getCreator1();
		c1.setCreator_id(10);
		Optional<Creator> pop=Optional.ofNullable(c1);
		Mockito.when(repo.findById(Mockito.anyInt())).thenReturn(pop);
		Assertions.assertEquals(service.getCreator(10), CreatorUtil.convertToCreatorDTO(c1));
		
	}
	
//	@Test
//	public void testUpdateName() throws Exception{
//		Creator c1 = getCreator1();
//		int id = c1.getCreator_id();
//		CreatorDTO dto = CreatorUtil.convertToCreatorDTO(c1);
//		Optional<Creator> c=Optional.ofNullable(c1);
//		Mockito.when(repo.findById(id)).thenReturn(c);
//		Assertions.assertEquals(service.updateName(id, dto),c1);
//	}
//	@Test
//	public void testUpdateEmail() throws Exception{
//		Creator c1 = getCreator1();
//		int id = c1.getCreator_id();
//		CreatorDTO dto = CreatorUtil.convertToCreatorDTO(c1);
//		Optional<Creator> c=Optional.ofNullable(c1);
//		Mockito.when(repo.findById(id)).thenReturn(c);
//		Assertions.assertEquals(service.updateEmail(id, dto),c1);
//	}
	
	@Test
	public void testAddContest() throws Exception{
		Creator c1 = getCreator1();
		int id = c1.getCreator_id();
		Set <Contest> l = c1.getContest();
		@SuppressWarnings("unchecked")
		ArrayList<Contest> lis =(ArrayList<Contest>)l;
		Optional<Creator> pop=Optional.ofNullable(c1);
		Mockito.when(repo.findById(id)).thenReturn(pop);
		Assertions.assertEquals(service.addContest(id, ContestUtils.convertToContestDtoList(lis)), CreatorUtil.convertToCreatorDTO(c1));
		
	}
	 //getting 1 Creator
    Creator getCreator1() {
    	Creator c = new Creator();
    	c.setEmail("abc@abc");
    	c.setContest(null);
    	c.setName("agni");
    	c.setCreator_id(1);
    	return c;
    }
    
    //getting 2nd Creator
    Creator getCreator2() {
    	Creator c =new Creator();
    	c.setName("agnidipto");
    	c.setEmail("oops@abc");
    	c.setCreator_id(2);
    	c.setContest(null);
    	
    	return c;
    }
	
	
}