package com.cg.jpautils;

import java.util.ArrayList;
import java.util.List;

import com.cg.dto.AdminDTO;
import com.cg.entities.Admin;

public class AdminUtil {
	
	private AdminUtil() {}

	public static AdminDTO getAdminDTO(Admin admin) {
		AdminDTO adto=new AdminDTO();
		adto.setAdminId(admin.getAdminId());
		adto.setEmail(admin.getEmail());
		adto.setName(admin.getName());
		adto.setPassword(admin.getPassword());
		
		return adto;
	}
	
	public static Admin getAdmin(AdminDTO adto) {
		Admin admin=new Admin();
		admin.setAdminId(adto.getAdminId());
		admin.setEmail(adto.getEmail());
		admin.setName(adto.getName());
		admin.setPassword(adto.getPassword());
		
		return admin;
	}
	
	public static List<Admin> getAdminList(List<AdminDTO> adtolist){
		List<Admin> adminlist=new ArrayList<>();
		
		for(AdminDTO adto: adtolist) {
			adminlist.add(getAdmin(adto));
		}
		
		return adminlist;
	}
	
	public static List<AdminDTO> getAdminDTOList(List<Admin> adminlist){
		List<AdminDTO> adtolist=new ArrayList<>();
		
		for(Admin admin:adminlist) {
			adtolist.add(getAdminDTO(admin));
		}
		
		return adtolist;
	}
}
