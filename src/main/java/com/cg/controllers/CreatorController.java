package com.cg.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.ContestDTO;
import com.cg.dto.CreatorDTO;
import com.cg.entities.Creator;
import com.cg.exceptions.CreatorNotFoundException;
import com.cg.jpautils.CreatorUtil;
import com.cg.service.CreatorService;

@RestController
@CrossOrigin(origins="*")
public class CreatorController {
	private static final Logger logger = LogManager.getLogger(CreatorController.class);

	@Autowired
	private CreatorService service;
	
	
	@GetMapping("/creator")
	public ResponseEntity<List<CreatorDTO>> getCreators(){
		logger.info("Entered in creator controller method getCreators");
		List<CreatorDTO> creatorDto =  service.getCreators();
		logger.info("Done in creator controller method getCreators");
		return new ResponseEntity<List<CreatorDTO>>(creatorDto,HttpStatus.OK);
	}
	@PostMapping(value = "/creator")
	public ResponseEntity<Creator> addCreator(@RequestBody CreatorDTO creatorDto){
		logger.info("Entered in creator controller method addCreator");
		Creator creator=service.addCreator(creatorDto);
		logger.info("Done in creator controller method addCreator");
		return new ResponseEntity<Creator>(creator,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/creator/{id}")
	public ResponseEntity<CreatorDTO> getCreator(@PathVariable int id) throws CreatorNotFoundException{
		logger.info("Entered in creator controller method getCreator");
		CreatorDTO creatorDto = service.getCreator(id);
		logger.info("Done in creator controller method getCreator");
		return new ResponseEntity<CreatorDTO>(creatorDto, HttpStatus.OK);
	}
	
	@PutMapping(value="/creator/{id}/update")
	public ResponseEntity<CreatorDTO> updateCreator(@PathVariable Integer id, @RequestBody CreatorDTO creatorDto) throws CreatorNotFoundException {
		logger.info("Entered in creator controller method updateCreator");
		Creator c = service.updateCreator(id,creatorDto);
		CreatorDTO creator = CreatorUtil.convertToCreatorDTO(c);
		logger.info("Done in creator controller method updateCreator");
		return new ResponseEntity<CreatorDTO>(creator, HttpStatus.OK);
	}
	
	@DeleteMapping("/creator/{id}/delete")
	public boolean deleteCreator(@PathVariable Integer id) throws CreatorNotFoundException {
		logger.info("Entered in creator controller method deleteCreator");
		boolean deleted = service.deleteCreator(id);
		logger.info("Done in quiz controller method deleteQuiz");
		return deleted;
	}	
	
	@PostMapping(value = "creator/{creatorId}/contest")
	public ResponseEntity<CreatorDTO> addContest(@PathVariable Integer creatorId, @RequestBody List<ContestDTO> Contest) throws CreatorNotFoundException {
		logger.info("Entered in creator controller method addContest");
		CreatorDTO creator = service.addContest(creatorId, Contest);
		logger.info("Done in creator controller method addContest");
		return new ResponseEntity<CreatorDTO>(creator, HttpStatus.OK);
	}
	
	
	

	
	
	
	

}
