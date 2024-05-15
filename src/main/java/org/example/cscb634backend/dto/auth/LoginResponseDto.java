package org.example.cscb634backend.dto.auth;

import org.example.cscb634backend.entity.auth.MyUser;

public class LoginResponseDto {
	private String email;
	private String bearer = "Bearer ";
	private String jwt;
	
	public LoginResponseDto(){
		super();
	}
	public LoginResponseDto(String email, String jwt){
		this.email=email;
		this.jwt = jwt;
		
	}
	
	public String getBearer() {
		return bearer;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getJwt(){
		return this.jwt;
	}
	
	public void setJwt(String jwt){
		this.jwt = jwt;
	}
	
	
}
