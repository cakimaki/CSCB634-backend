package org.example.cscb634backend.controller.auth;

import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.service.auth.MyUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/admin")
public class MyUserController {
	private final MyUserService myUserService;
	
	public MyUserController(MyUserService myUserService){
		this.myUserService = myUserService;
	}
	@PostMapping("/create")
	public ResponseEntity<MyUser> createUser(MyUserDto dto){
		try {
			MyUser user = myUserService.createUser(dto);
			return ResponseEntity.ok(user);
		}catch(Exception e){
			throw new RuntimeException("creation of user failed.");
		}
	}
}
