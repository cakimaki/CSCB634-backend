package org.example.cscb634backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private UserDetailsService userDetailsService;
	@Bean
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
		return http
				.cors(cors -> cors.configurationSource(corsConfigurationSource)) // Use the defined CORS configuration
				.csrf(AbstractHttpConfigurer::disable) //stopped csrf protection (postman)
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/auth/perform_register", "/api/auth/perform_register/**", "/home", "/").permitAll()
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/moderator/**").hasRole("MODERATOR")
						.requestMatchers("/api/user/**").hasRole("USER")
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults()
				)
			/*	.formLogin(form -> form
						.permitAll() // Allow form login
						.defaultSuccessUrl("/home", true) // Redirect to home on success
				)*/
				.build();
	}
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://example.com")); // Add your allowed origins here
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Specify allowed methods
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-CSRF-TOKEN"));
		configuration.setAllowCredentials(true);
		configuration.setMaxAge(3600L); // How long the response to the pre-flight request can be cached
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Apply CORS configuration to all paths
		return source;
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

}
