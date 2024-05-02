package org.example.cscb634backend.service.auth;


import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.auth.Role;
import org.example.cscb634backend.repository.auth.MyUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final MyUserRepository myUserRepository;
	
	public CustomUserDetailsService(MyUserRepository myUserRepository) {
		this.myUserRepository = myUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<MyUser> userOptional = myUserRepository.findByEmail(email);
		if(userOptional.isPresent()){
			MyUser user = userOptional.get();
			return User.builder()
					.username(user.getUsername())
					.password(user.getPassword())
					.roles(getRoles(user))
					.build();
		}else{
			throw new UsernameNotFoundException("email is not found. - '" + email + "'");
		}
	}
	
	private String[] getRoles(MyUser user){
		if (user.getRoleList() == null || user.getRoleList().isEmpty()){
			return new String[]{"USER"}; //default role if roles is empty.
		}
		
		return user.getRoleList().stream()
				.map(Role::getName)
				.toArray(String[]::new);
	}
}
