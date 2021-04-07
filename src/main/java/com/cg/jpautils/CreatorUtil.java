package com.cg.jpautils;

import com.cg.dto.CreatorDTO;
import com.cg.entities.Creator;

import java.util.*;


public class CreatorUtil {
	//get list of creators dto from creator list
	
	public static List<CreatorDTO> convertToCreatorDtoList(List<Creator> list)
	{
		List<CreatorDTO> creatorDtoList = new ArrayList<CreatorDTO>();
		for(Creator creator : list) 
			creatorDtoList.add(convertToCreatorDTO(creator));
		return creatorDtoList;
	}
	
	//get list of creators from creator dto list
	
		public static List<Creator> convertToCreatorList(List<CreatorDTO> list)
		{
			List<Creator> creatorList = new ArrayList<Creator>();
			for(CreatorDTO creatorDto : list) 
				creatorList.add(convertToCreator(creatorDto));
			return creatorList;
		}
	
	//convert creatordto to creator
	
	public static Creator convertToCreator(CreatorDTO creatorDto) {
		Creator creator = new Creator();
		creator.setCreator_id(creatorDto.getCreator_id());
		creator.setEmail(creatorDto.getEmail());
		creator.setName(creatorDto.getName());
		creator.setContest(creatorDto.getContest());
		creator.setPaid(creatorDto.isPaid());
		creator.setPassword(creatorDto.getPassword());
		return creator;
	}
	
	//convert creator to creatorDto
	
	public static CreatorDTO convertToCreatorDTO(Creator creator) {
		CreatorDTO creatorDto = new CreatorDTO();
		creatorDto.setCreator_id(creator.getCreator_id());
		creatorDto.setEmail(creator.getEmail());
		creatorDto.setName(creator.getName());
		creatorDto.setContest(creator.getContest());
		creatorDto.setPaid(creator.isPaid());
		creatorDto.setPassword(creator.getPassword());
		return creatorDto;
	}
}
