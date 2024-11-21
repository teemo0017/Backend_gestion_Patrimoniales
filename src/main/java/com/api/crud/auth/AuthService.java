package com.api.crud.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.crud.Jwt.JwtService;
import com.api.crud.models.Role;
import com.api.crud.models.UserModels;
import com.api.crud.repositories.IUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

private final IUserRepository userRepository;
private final JwtService jwtService;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;

	public authResponse login(LoginRequest request) {
		
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return authResponse.builder()
            .token(token)
            .build();
	}
	
	public authResponse register(registerRequest request) {
		
		Role rol = Role.USER;
		if(request.getRole().equals("ADMIN")) {
			rol = Role.ADMIN;}
		
	UserModels user = UserModels.builder()
			.username(request.getUsername())
			.password(passwordEncoder.encode(request.getPassword()))
			.firstName(request.getFirstname())
			.lastName(request.getLastname())
			.country(request.getCountry())
			.email(request.getEmail())
			.role(rol)
			.age(request.getAge())
			.phone(request.getPhone())
			.build();
	userRepository.save(user);
	
	
	return authResponse.builder()
			.token(jwtService.getToken(user))
			.build();
	}

}
