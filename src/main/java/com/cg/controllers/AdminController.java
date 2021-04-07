package com.cg.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.AdminDTO;
import com.cg.dto.CreatorDTO;
import com.cg.dto.ParticipantDTO;
import com.cg.entities.Admin;
import com.cg.entities.Creator;
import com.cg.entities.Participant;
import com.cg.exceptions.AdminNotFoundException;
import com.cg.exceptions.CreatorNotFoundException;
import com.cg.exceptions.ParticipantNotFoundException;
import com.cg.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="*")
public class AdminController {
	private static final Logger logger = LogManager.getLogger(AdminController.class);

	@Autowired
	AdminService aservice;
	


	@GetMapping(value="/show/admin")
	public List<Admin> showAdmins(){
		logger.info("Entered in Admin controller method showAdmins");
		logger.info("Done in Admin controller method showAdmins");
		return aservice.showAdmins();
	}
	
	@PostMapping(value="/new/admin")
	public Admin createNewAdmin(@RequestBody AdminDTO adto){
		logger.info("Entered in Admin controller method createNewAdmin");
		logger.info("Done in Admin controller method createNewAdmin");
		return aservice.createNewAdmin(adto);
	}
	

	@PostMapping(value="/insert/creator")
	public Creator createNewCreator(@RequestBody CreatorDTO cdto) {
		logger.info("Entered in Admin controller method createNewCreator");
		logger.info("Done in Admin controller method createNewCreator");	
		return aservice.createNewCreator(cdto);
	}
	
	@PostMapping(value="/delete/creator/{creatorid}")
	public String deleteCreator(@PathVariable int creatorid) throws CreatorNotFoundException {
		logger.info("Entered in Admin controller method deleteCreator");
		aservice.deleteCreator(creatorid);
		logger.info("Done in Admin controller method deleteCreator");
		return "Creator Deleted";
	}
	
	@PostMapping(value="/insert/participant")
	public Participant createNewParticipant(@RequestBody ParticipantDTO pdto) {
		logger.info("Entered in Admin controller method createNewParticipant");
		logger.info("Done in Admin controller method createNewParticipant");
		return aservice.createNewParticipant(pdto);
	}
	
	@PostMapping(value="/delete/participant/{participantid}")
	public String deleteParticiapnt(@PathVariable int participantid,@PathVariable int adminid) throws ParticipantNotFoundException {

		logger.info("Entered in Admin controller method deleteParticipant");		
		aservice.deleteParticipant(participantid);
		logger.info("Done in Admin controller method deleteParticipant");
		return "Participant Deleted";
	}
	
	@GetMapping(value="/{adminid}")
	public Admin getAdminById(@PathVariable int adminid) throws AdminNotFoundException {
		logger.info("Entered in Admin controller method getAdminDTO");
		logger.info("Done in Admin controller method getAdminDTO");

		return aservice.getAdminById(adminid);
	}
	
}
