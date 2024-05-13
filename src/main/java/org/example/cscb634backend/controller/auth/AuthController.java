package org.example.cscb634backend.controller.auth;

import jakarta.validation.Valid;
import org.example.cscb634backend.dto.auth.LoginResponseDto;
import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.service.auth.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private MyUserService userService;
	//@Autowired
	//private AuthenticationManager authenticationManager;
	@PostMapping("/perform_register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody  MyUserDto dto){
		try{
			MyUser registeredUser = userService.registerUser(dto);
			return ResponseEntity.ok("Register has been successfull.");
		}catch(Exception e){
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registering failed: " + e.getMessage());
			
		}
	}
	
	//TODO
	//  NOT FINISHED.>>>>
	@PostMapping("/perform_login")
	public LoginResponseDto loginUser(@Valid @RequestBody MyUserDto dto){
		try{
			return userService.loginUser(dto.getEmail(),dto.getPassword());
		}catch(Exception e){
			throw new RuntimeException();
		}
	}
	
}
