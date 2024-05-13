package org.example.cscb634backend.dto.auth;

import org.example.cscb634backend.entity.auth.MyUser;

public class LoginResponseDto {
	private MyUser user;
	private String jwt;
	
	public LoginResponseDto(){
		super();
	}
	public LoginResponseDto(MyUser user, String jwt){
		this.user = user;
		this.jwt = jwt;
		
	}
	
	public MyUser getUser(){
		return this.user;
	}
	
	public void setUser(MyUser user){
		this.user=user;
	}
	
	public String getJwt(){
		return this.jwt;
	}
	
	public void setJwt(String jwt){
		this.jwt = jwt;
	}
	
	
}
