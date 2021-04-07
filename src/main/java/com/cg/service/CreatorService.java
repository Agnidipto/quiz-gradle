package com.cg.service;


import java.util.List;

import java.util.Optional;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.ContestDTO;
import com.cg.dto.CreatorDTO;
import com.cg.entities.Contest;
import com.cg.entities.Creator;
import com.cg.exceptions.CreatorNotFoundException;
import com.cg.jpautils.ContestUtils;
import com.cg.jpautils.CreatorUtil;
import com.cg.repo.ContestRepo;
import com.cg.repo.CreatorRepo;

@Service
public class CreatorService implements CreatorServiceInterface{
	private static final Logger logger = LogManager.getLogger(CreatorService.class);

	@Autowired
	CreatorRepo repo;
	
	//add new creator
	@Override
	public Creator addCreator(CreatorDTO creatorDto) {
		logger.info("Entered in creator service method addCreator");
		logger.info("done in creator service method addCreator");
		return repo.save(CreatorUtil.convertToCreator(creatorDto));
	}
	
	// get all the creators, only for testing purposes
	@Override
	public List<CreatorDTO> getCreators()    
	{
		
		logger.info("Entered in creator service method getCreators");
		List<Creator> list1= repo.findAll();     
		logger.info("Done in creator service method getCreators");
		return CreatorUtil.convertToCreatorDtoList(list1);
	}
	
	// get specific creator based on id
	@Override
	public CreatorDTO getCreator(int creatorId) throws CreatorNotFoundException{ 
		logger.info("Entered in creator service method getCreator");
		Optional<Creator> optional = repo.findById(creatorId);
		
		if (optional.isPresent()) {
			Creator creator = optional.get();
			logger.info("Done in creator service method getCreator");
			return CreatorUtil.convertToCreatorDTO(creator);
		}
		else {
			throw new CreatorNotFoundException("No such creator found");
		}
		
		
	};
	
	// Update creator
	@Override
	public Creator updateCreator(int id,CreatorDTO creatordto) throws CreatorNotFoundException{     
		logger.info("Entered in creator service method updateName");
		Optional<Creator> optional = repo.findById(id); 
		Creator creator;
		if (optional.isPresent()) {
			creator = optional.get();
			creator.setName(creatordto.getName());
			creator.setEmail(creatordto.getEmail());
			creator.setPassword(creatordto.getPassword());
			
		}
		else {
			 throw new CreatorNotFoundException("No such creator found");
		}
		repo.flush();
		return creator;
	}; 
	
	//delete creator
	@Override
	public boolean deleteCreator(Integer creator_id) throws CreatorNotFoundException {
		logger.info("Entered in creator service method deleteCreator");
		Optional<Creator> c = repo.findById(creator_id);
		if (!c.isPresent()) {
			throw new CreatorNotFoundException("Creatornot found");
		}
		repo.delete(c.get());
		logger.info("Done in Creator service method deleteCreator");
		return true;
	}
	
	// Creator add contest feature
	@Override
	public CreatorDTO addContest(int creatorId, List<ContestDTO> Contests) throws CreatorNotFoundException {
		logger.info("Entered in creator service method addContest");
		List<Contest> ContestEntities = ContestUtils.convertToContestList(Contests);
		Optional<Creator> c = repo.findById(creatorId);
		if (!c.isPresent()) {
			throw new CreatorNotFoundException("No such Creator found");
		}
		Creator Creator = c.get();
		Creator.getContest().addAll(ContestEntities);
		Creator = repo.saveAndFlush(Creator);
		logger.info("Done in creator service method addContest");
		return CreatorUtil.convertToCreatorDTO(Creator);
	}

	
	
	// Boolean makePayment(int pay); Will be added afterwards
	
	
}
