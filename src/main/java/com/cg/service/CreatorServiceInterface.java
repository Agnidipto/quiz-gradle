package com.cg.service;

import java.util.List;

import com.cg.dto.ContestDTO;
import com.cg.dto.CreatorDTO;
import com.cg.entities.Creator;
import com.cg.exceptions.CreatorNotFoundException;

public interface CreatorServiceInterface {
	public List<CreatorDTO> getCreators();
	public CreatorDTO getCreator(int creatorId) throws CreatorNotFoundException;
	public Creator updateCreator(int creatorId,CreatorDTO creatorDto) throws CreatorNotFoundException;

	boolean deleteCreator(Integer creator_id) throws CreatorNotFoundException ;
	
	CreatorDTO addContest(int creatorId, List<ContestDTO> Contests) throws CreatorNotFoundException;
	Creator addCreator(CreatorDTO creatorDto);
	
	
}
