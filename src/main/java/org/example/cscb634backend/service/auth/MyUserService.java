package org.example.cscb634backend.service.auth;

import org.example.cscb634backend.dto.auth.LoginResponseDto;
import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;

public interface MyUserService {
	
	MyUser createUser(MyUserDto dto);
	
	MyUser registerUser(MyUserDto dto);
	
	MyUser updateUser(Long userId, MyUserDto userDto);
	
	MyUser findUser(MyUserDto dto);
	
	LoginResponseDto loginUser(String email, String password);
	
	MyUserDto getUserByIdOrName(MyUserDto dto);
	
	MyUserDto deleteUser(Long userId);
}
