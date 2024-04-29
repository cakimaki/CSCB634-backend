package org.example.cscb634backend.service;

import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.repository.MyUserRepository;
import org.example.cscb634backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserServiceImpl implements MyUserService{
	private MyUserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public MyUserServiceImpl(MyUserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public MyUser saveUser(MyUser user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		List<Role> managedRoles = manageRoles(user.getRoleList());
		user.setRoleList(managedRoles);
		return userRepository.save(user);
	}
	
	private List<Role> manageRoles(List<Role> roles){
		if(roles == null ) return null;
		List<Role> persistedRoles = new ArrayList<>();
		for(Role role: roles){
			Role persistedRole = roleRepository.findByName(role.getName())
					.orElseGet(()->roleRepository.save(role));
			persistedRoles.add(persistedRole);
		}
		return persistedRoles;
	}
}
