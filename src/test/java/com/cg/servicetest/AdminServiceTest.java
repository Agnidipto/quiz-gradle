package com.cg.servicetest;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cg.dto.AdminDTO;
import com.cg.dto.CreatorDTO;
import com.cg.dto.ParticipantDTO;
import com.cg.dto.QuestionDTO;
import com.cg.dto.QuizDTO;
import com.cg.entities.Admin;
import com.cg.entities.Creator;
import com.cg.entities.Participant;
import com.cg.entities.Quiz;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.exceptions.QuizNotFoundException;
import com.cg.jpautils.AdminUtil;
import com.cg.jpautils.CreatorUtil;
import com.cg.jpautils.ParticipantUtil;
import com.cg.jpautils.QuizUtils;
import com.cg.repo.AdminRepo;
import com.cg.repo.CreatorRepo;
import com.cg.repo.ParticipantRepo;
import com.cg.repo.QuizRepo;
import com.cg.service.AdminService;
import com.cg.service.CreatorService;
import com.cg.service.ParticipantService;

import junit.framework.Assert;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AdminServiceTest {

	@MockBean
	private AdminRepo arepo;
	@MockBean
	private CreatorRepo crepo;
	@MockBean 
	private ParticipantRepo prepo;
	@MockBean
	private CreatorService creservice;
	@MockBean
	private ParticipantService pservice;
	
	@Autowired
	AdminService aservice;
	
	@Test
	void createNewAdmin() throws Exception{
		
		Admin a=getAdmin1();
		AdminDTO adto = AdminUtil.getAdminDTO(a);
		Mockito.when(arepo.saveAndFlush(Mockito.any(Admin.class))).thenReturn(a);
		Assertions.assertEquals(aservice.createNewAdmin(adto), a);
	}
	
	@Test
	void findAdminById() throws Exception {
		Admin a=getAdmin1();
		arepo.saveAndFlush(a);
		Mockito.when(arepo.findById(16).get()).thenReturn(a);
		Assertions.assertEquals(aservice.getAdminById(16), a);
	}
	
	@Test
	void createNewCreator() throws Exception{
		Creator c=new Creator();
		c.setName("agni");
		CreatorDTO cdto=CreatorUtil.convertToCreatorDTO(c);
		
		Mockito.when(crepo.saveAndFlush(Mockito.any(Creator.class))).thenReturn(c);
		Assertions.assertEquals(aservice.createNewCreator(cdto), c);
	}
	
	@Test
	void deleteCreator() throws Exception{
		Creator c=new Creator();
		c.setName("agni");
		CreatorDTO cdto=CreatorUtil.convertToCreatorDTO(c);

		Mockito.when(creservice.getCreator(1)).thenReturn(cdto);
		Assertions.assertEquals("deleted", aservice.deleteCreator(1));
	}
	
	@Test 
	void createNewParticipant() throws Exception{
		Participant p=new Participant();
		p.setName("agni");
		p.setParticipantId(1);
		p.setEmail("abc");
		p.setContest(null);
		ParticipantDTO pdto=ParticipantUtil.getParticipantDTO(p);
		
		Mockito.when(pservice.createNewParticipant(pdto)).thenReturn(p);
		Assertions.assertEquals(aservice.createNewParticipant(pdto), p);
	}
	
	@Test
	void deleteParticipant() throws Exception{
		Admin a=getAdmin1();
		
		Participant p=new Participant();
		p.setName("agni");
		Mockito.when(pservice.getParticipantById(Mockito.anyInt())).thenReturn(p);
		Mockito.when(pservice.deleteParticipant(Mockito.anyInt())).thenReturn("Deleted");
		Assertions.assertEquals("deleted",aservice.deleteParticipant(16));
	}
	
	@Test
	void showAdmins() throws Exception {
		Admin a1=getAdmin1();
		Admin a2=getAdmin2();
		
		List<Admin> a=new ArrayList<>();
		a.add(a1);
		a.add(a2);
		
		Mockito.when(arepo.findAll()).thenReturn(a);
		Assertions.assertEquals(aservice.showAdmins(), a);
	}
	
	//getting 1 object of Admin
    Admin getAdmin1() {
    	Admin p=new Admin();
    	p.setAdminId(16);
    	p.setEmail("agnid");
    	p.setName("agni");
    	return p;
    }
    
    //getting 2nd object of Admin
    Admin getAdmin2() {
    	Admin p =new Admin();
    	p.setAdminId(16);
    	p.setName("agni");
    	p.setEmail("agnid");    	
    	return p;
    }
}
