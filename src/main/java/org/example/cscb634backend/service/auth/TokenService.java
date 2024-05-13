package org.example.cscb634backend.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {
	private final JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;
	
	public TokenService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
		this.jwtEncoder = jwtEncoder;
		this.jwtDecoder = jwtDecoder;
	}
	
	public String generateJwt(Authentication auth){
		
		Instant now = Instant.now();
		
		List<String> roles = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toList();

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.subject(auth.getName())
				.claim("roles", roles)
				.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}
