package com.cg.service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.AdminDTO;
import com.cg.dto.CreatorDTO;
import com.cg.dto.ParticipantDTO;
import com.cg.entities.Admin;
import com.cg.entities.Creator;
import com.cg.entities.Participant;
import com.cg.exceptions.AdminNotFoundException;
import com.cg.exceptions.CreatorNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.jpautils.AdminUtil;
import com.cg.jpautils.CreatorUtil;
import com.cg.repo.AdminRepo;
import com.cg.repo.CreatorRepo;

@Service
public class AdminService {
	private static final Logger logger = LogManager.getLogger(AdminService.class);

	@Autowired
	AdminRepo arepo;
	
	@Autowired
	CreatorRepo creatorrepo;
	
	@Autowired
	CreatorService creservice;
	
	@Autowired 
	ParticipantService pservice;
	

	public List<Admin> showAdmins(){
		logger.info("Entered in admin service method showAdmins");
		List<Admin> alist=arepo.findAll();
		logger.info("Done in admin service method showAdmins");
		return alist;
	}
	
	public Admin createNewAdmin(AdminDTO adto) {
		logger.info("Entered in admin service method createNewAdmin");
		Admin admin=AdminUtil.getAdmin(adto);
		logger.info("Done in admin service method createNewAdmin");
		return arepo.saveAndFlush(admin);
	}
	

	public Creator createNewCreator(CreatorDTO cdto) {
		logger.info("Entered in admin service method createNewCreator");
		//getAdminById(adminid);
		Creator creator=CreatorUtil.convertToCreator(cdto);
		logger.info("Done in admin service method createNewCreator");
		return creatorrepo.saveAndFlush(creator);
	}


	public String deleteCreator(int creatorid) throws CreatorNotFoundException {
		logger.info("Entered in admin service method deleteCreator");
		//getAdminById(adminid);
		creservice.getCreator(creatorid);
		creatorrepo.deleteById(creatorid);
		logger.info("Done in admin service method deleteCreator");
		return "deleted";
	}

	public Participant createNewParticipant(ParticipantDTO pdto) {
		logger.info("Entered in admin service method createNewParticipant");
		//getAdminById(adminid);
		logger.info("Done in admin service method createNewParticipant");
		return pservice.createNewParticipant(pdto);
	}

	public String deleteParticipant(int participantid) throws ParticipantNotFoundException {
		logger.info("Entered in admin service method deleteParticipant");
		pservice.getParticipantById(participantid);
		logger.info("Done in admin service method deleteParticipant");
		pservice.deleteParticipant(participantid);
		return "deleted";
	}

	public Admin getAdminById(int adminid) throws AdminNotFoundException {
		
		logger.info("Entered in admin service method getAdminById");
		Optional<Admin> adminoptional=arepo.findById(adminid);
		if(!adminoptional.isPresent())
			throw new AdminNotFoundException("Admin not found for id");
		
		logger.info("Done in admin service method getAdminById");
		return adminoptional.get();

	}
}
