package org.example.cscb634backend.controller.auth;

import jakarta.validation.Valid;
import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.service.auth.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class AuthController {
	
	@Autowired
	private MyUserService userService;
	@PostMapping("/perform_register")
	public ResponseEntity<MyUser> registerUser(@Valid @RequestBody  MyUserDto dto){
		try{
			MyUser registeredUser = userService.registerUser(dto);
			return ResponseEntity.ok(registeredUser);
		}catch(Exception e){
			throw new RuntimeException("Registering failed.");
		}
	}
	
	//TODO
	//  NOT FINISHED.>>>>
	@PostMapping("/perform_login")
	public ResponseEntity<MyUser> loginUser(@Valid @RequestBody MyUserDto dto){
		try{
			MyUser user = new MyUser();
			return ResponseEntity.ok(user);
		}catch(Exception e){
			throw new RuntimeException();
		}
		
	}
	
}
