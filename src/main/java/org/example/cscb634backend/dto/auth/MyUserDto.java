package org.example.cscb634backend.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.cscb634backend.entity.auth.Role;

import java.util.List;

@Setter
@Getter
public class MyUserDto {
	private Long id;
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@NotBlank
	private String password;
	

	private List<Role> roles;
	
	
}
