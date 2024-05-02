package org.example.cscb634backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable) //stopped csrf protection (postman)
				.authorizeHttpRequests(registry -> {
					registry.requestMatchers("/home","/login","/register/**").permitAll();
					registry.requestMatchers("/admin/**").hasRole("ADMIN");
					registry.requestMatchers("/moderator/**").hasRole("MODERATOR");
					registry.requestMatchers("/user/**").hasRole("USER");
					registry.anyRequest().authenticated(); //all requests authenticated
				})
				.formLogin(form->form
						.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/home",true)
						.permitAll()
				)
				.logout(LogoutConfigurer::permitAll
				)
				
				//.formLogin(AbstractAuthenticationFilterConfigurer::permitAll) //form is for all
				.build();
	}
	
/*	@Bean
	public UserDetailsService userDetailsService(){
		UserDetails normalUser = User.builder()
				.username("gc")
				.password("$2a$12$UWkJZvySvaSwyz59EIUxC.u8UGGvMIIWOEUEojGstNfmhuWhHnM0W")
				.roles("USER")
				.build();
		UserDetails adminUser = User.builder()
				.username("admin")
				.password("$2a$12$5PLtUX88US1M/qydLpTL3Oz0bb9sqb5MUvzdR/aZrDxen4IK3Cwd.")
				.roles("USER","ADMIN")
				.build();
		return new InMemoryUserDetailsManager(normalUser,adminUser);
	}*/
	@Bean
	public UserDetailsService userDetailsService(){
		return userDetailsService;
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
