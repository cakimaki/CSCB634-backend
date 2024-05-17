package org.example.cscb634backend.dto.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.cscb634backend.entity.auth.Role;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Setter
@Getter
@Validated
public class MyUserDto {
	private Long id;
	
	@NotNull
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
	

	private List<Role> roles;
	
	public MyUserDto() {
	}
	
	public MyUserDto(Long id, String email, String password, List<Role> roles) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public MyUserDto(Long id, String email, List<Role> roles) {
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
