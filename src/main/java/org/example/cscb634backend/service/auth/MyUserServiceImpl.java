package org.example.cscb634backend.service.auth;

import org.example.cscb634backend.dto.auth.LoginResponseDto;
import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.repository.auth.MyUserRepository;
import org.example.cscb634backend.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MyUserServiceImpl implements MyUserService {
	private final MyUserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Autowired
	public MyUserServiceImpl(MyUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	
	public MyUser loginUser(MyUserDto dto){
		MyUser user = new MyUser();
		
		
		
		return user;
	}
	@Override
	public MyUser createUser(MyUserDto dto) {
		MyUser user = new MyUser();
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setDeleted(false);
		List<Role> managedRoles = manageRoles(dto.getRoles());
		
		user.setRoleList(managedRoles);
		return userRepository.save(user);
	}
	private List<Role> manageRoles(List<Role> roles) {
		if (roles == null) return null;
		List<Role> persistedRoles = new ArrayList<>();
		for (Role role : roles) {
			Role persistedRole = roleRepository.findByName(role.getName())
					.orElseGet(() -> roleRepository.save(role));
			persistedRoles.add(persistedRole);
		}
		return persistedRoles;
	}
	
	@Override
	public MyUser registerUser(MyUserDto dto) {
		if (dto.getPassword() == null || dto.getEmail() == null) {
			throw new IllegalArgumentException("Username, password or email must not be null");
		}
		MyUser user = new MyUser();
		if(userRepository.existsByEmail(dto.getEmail())){
			throw new IllegalArgumentException("Email has been already registered.");
		}
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRoleList(getDefaultRole());
		user.setDeleted(false);
		return userRepository.save(user);
	}

	@Override
	public LoginResponseDto loginUser(String username, String password){
		
		try{
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password)
			);
			
			String token = tokenService.generateJwt(auth);
			
			return new LoginResponseDto(userRepository.findByEmail(username).get().getEmail(), token);
			
		} catch(AuthenticationException e){
			return new LoginResponseDto(null, "");
		}
	}
	private List<Role> getDefaultRole() {
		String defaultRole = "USER";
		Optional<Role> optionalRole = roleRepository.findByName(defaultRole);
		
		if(optionalRole.isEmpty()){
		Role newRole = new Role(defaultRole);
		roleRepository.save(newRole);
		return Collections.singletonList(newRole);
		}
		return Collections.singletonList(optionalRole.get());
	}
	
	@Override
	public MyUserDto getUserByIdOrName(MyUserDto dto) {
		MyUser userOptional = findUser(dto);
		return convertToDto(userOptional);
	}
	
	public MyUser findUser(MyUserDto dto) {
		if (dto.getEmail() != null) {
			Optional<MyUser> userByEmail = userRepository.findByEmail(dto.getEmail());
			if (userByEmail.isPresent()) {
				return userByEmail.get();
			}
		}
		if (dto.getId() != null) {
			Optional<MyUser> userById = userRepository.findById(dto.getId());
			if (userById.isPresent()) {
				return userById.get();
			}
		}
		throw new NoSuchElementException("No such user is found or no input is made");
	}
	@Override
	public MyUser updateUser(Long userId, MyUserDto userDto) {
		MyUser user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User with specified id is not found. - " + userId));
		
		//update
		if(userDto.getEmail()!= null || !(userDto.getEmail().isEmpty())){
			user.setEmail(userDto.getEmail());
		}
		user.setEmail(userDto.getEmail());
		if (userDto.getPassword() != null || !(user.getPassword().isEmpty())) {
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		}
		if (userDto.getRoles() != null || !(user.getRoleList().isEmpty())) {
			user.setRoleList(userDto.getRoles());
		}
		return user;
	}
	
	
	private MyUserDto convertToDto(MyUser user) {
		MyUserDto dto = new MyUserDto();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setRoles(user.getRoleList());
		
		return dto;
	}
	

	
	@Override
	public MyUserDto deleteUser(Long userId){
		MyUser user = userRepository.findById(userId)
				.orElseThrow(()-> new NoSuchElementException("No user is found with id: " + userId));
		
		user.setDeleted(true);
		
		return convertToDto(user);
	}
}
