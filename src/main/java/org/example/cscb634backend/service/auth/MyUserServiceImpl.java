package org.example.cscb634backend.service.auth;

import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.repository.auth.MyUserRepository;
import org.example.cscb634backend.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserServiceImpl implements MyUserService {
	private MyUserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public MyUserServiceImpl(MyUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public MyUser createUser(MyUserDto dto) {
		MyUser user = new MyUser();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		user.setDeleted(false);
		List<Role> managedRoles = manageRoles(dto.getRoles());
		
		user.setRoleList(managedRoles);
		return userRepository.save(user);
	}
	
	@Override
	public MyUser registerUser(MyUserDto dto) {
		if (dto.getUsername() == null || dto.getPassword() == null || dto.getEmail() == null) {
			throw new IllegalArgumentException("Username, password or email must not be null");
		}
		MyUser user = new MyUser();
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		user.setRoleList(manageRoles(getDefaultRole()));
		user.setDeleted(false);
		return userRepository.save(user);
	}
	
	private List<Role> getDefaultRole() {
		return Collections.singletonList(roleRepository.findByName("USER").orElse(new Role("USER")));
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
		if (dto.getUsername() != null) {
			Optional<MyUser> userByUsername = userRepository.findByUsername(dto.getUsername());
			if (userByUsername.isPresent()) {
				return userByUsername.get();
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
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		if (userDto.getPassword() != null || !(user.getPassword().isEmpty())) {
			user.setPassword(userDto.getPassword());
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
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRoles(user.getRoleList());
		
		return dto;
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
	public MyUserDto deleteUser(Long userId){
		MyUser user = userRepository.findById(userId)
				.orElseThrow(()-> new NoSuchElementException("No user is found with id: " + userId));
		
		user.setDeleted(true);
		
		return convertToDto(user);
	}
}
