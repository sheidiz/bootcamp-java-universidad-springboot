package ar.com.educacionit.movie.security.controllers;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.educacionit.movie.security.dto.AuthenticationRequest;
import ar.com.educacionit.movie.security.service.JpaUserDetailsService;
import ar.com.educacionit.movie.security.utils.JwtUtils;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager; 
	private final JpaUserDetailsService JpaUserDetailsService;
	private final JwtUtils jwtUtils;//por ser un @Component

	@PostMapping
	public ResponseEntity<String> authenticate (
			@Validated  @RequestBody AuthenticationRequest request  
			) {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
						request.getUsername(), 
						request.getPassword(),
						new ArrayList<>() 
					));
			
			final UserDetails user = JpaUserDetailsService.loadUserByUsername(request.getUsername());
			if(user != null) {
				String jwt = jwtUtils.generateToken(user);
				return ResponseEntity.ok(jwt);
			}
			
			return ResponseEntity.status(400).body("Error en la autenticación");
		}catch(Exception e) {
			return ResponseEntity.status(400).body(e.getMessage());
		}
		
	}
}
