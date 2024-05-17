package org.example.cscb634backend.dto.auth;

import org.example.cscb634backend.entity.auth.Role;

import java.util.List;

public class MyUserFrontDto {
	private Long id;
	private String email;
	private List<Role> roleList;
	
	public MyUserFrontDto() {
	}
	
	public MyUserFrontDto(Long id, String email, List<Role> roleList) {
		this.id = id;
		this.email = email;
		this.roleList = roleList;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
}
