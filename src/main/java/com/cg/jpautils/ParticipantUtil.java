package com.cg.jpautils;

import java.util.ArrayList;
import java.util.List;

import com.cg.dto.ParticipantDTO;
import com.cg.entities.Participant;

public class ParticipantUtil {
	
	private ParticipantUtil() { }

	public static Participant getParticipant(ParticipantDTO pto) {
		Participant p=new Participant();
		p.setContest(pto.getContest());
		p.setEmail(pto.getEmail());
		p.setName(pto.getName());
		p.setParticipantId(pto.getParticipantId());
		p.setPassword(pto.getPassword());
		
		return p;
	}
	
	public static ParticipantDTO getParticipantDTO(Participant p) {
		ParticipantDTO pdto= new ParticipantDTO();
		pdto.setContest(p.getContest());
		pdto.setEmail(p.getEmail());
		pdto.setName(p.getName());
		pdto.setParticipantId(p.getParticipantId());
		pdto.setPassword(p.getPassword());
		
		return pdto;
	}
	
	public static List<ParticipantDTO> getParticipantDTOList(List<Participant> plist) {
		List<ParticipantDTO> pdtolist=new ArrayList<>();
		ParticipantDTO pdto;
		
		for(Participant p:plist) {
			pdto=getParticipantDTO(p);
			pdtolist.add(pdto);
		}
		
		return pdtolist;
	}
}
