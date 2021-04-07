package com.cg.repotest;

import com.cg.entities.Admin;
import com.cg.repo.AdminRepo;

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

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AdminRepoTest {

	@Autowired
	private AdminRepo arepo;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	//testing storing admin 
	@Test
	void testParticipant() throws Exception {
		Admin a = new Admin();
		Admin saveInDb = testEntityManager.persist(a);
		Admin getFromInDb = arepo.findById(a.getAdminId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
	}

    
    //testing getting admin by id
    @Test
    void testGetAdminId() throws Exception {
    	Admin a=getAdmin1();
    	Admin saveInDb = testEntityManager.persist(a);
		Admin getFromInDb = arepo.findById(a.getAdminId()).get();
		assertThat(getFromInDb).isEqualTo(saveInDb);
    }
    
    //testing getting all admin
    @Test
    void testGetAllAdmins() throws Exception{
    	Admin a1= getAdmin1();
    	Admin a2=getAdmin2();
    	
    	testEntityManager.persist(a1);
    	testEntityManager.persist(a2);
    	
    	List<Admin> list= arepo.findAll();
    	Assert.assertEquals(2, list.size());
    }
    
    //testing deleting by admin
    @Test
    void deleteAdminById() throws Exception {
    	Admin p1=getAdmin1();
    	Admin p2= getAdmin2();
    	
    	Admin p3 = testEntityManager.persist(p1);
        testEntityManager.persist(p2);

        //delete one ticket DB
        testEntityManager.remove(p3);

        List<Admin> alist = arepo.findAll();
        Assert.assertEquals(1, alist.size());
    }
    
    //testing update admin
    @Test
    void UpdateAdmin() throws Exception {
    	Admin a=getAdmin1();
    	
    	testEntityManager.persist(a);
    	
    	Admin ad=arepo.findById(a.getAdminId()).get();
    	ad.setName("Dew");

    	testEntityManager.persist(ad);
    	
    	assertThat(ad.getName()).isEqualTo("Dew");
    }
    
    //getting 1 object of Admin
    Admin getAdmin1() {
    	Admin p=new Admin();
    	p.setEmail("abc@abc");
    	p.setName("agni");
    	return p;
    }
    
    //getting 2nd object of Admin
    Admin getAdmin2() {
    	Admin p =new Admin();
    	p.setName("agnidipto");
    	p.setEmail("oops@abc");    	
    	return p;
    }
}
