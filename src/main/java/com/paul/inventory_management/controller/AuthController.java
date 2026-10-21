package com.paul.inventory_management.controller;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paul.inventory_management.dto.AppUserResponseDTO;
import com.paul.inventory_management.dto.LoginRequestDTO;
import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.security.CustomUserDetailsService;
import com.paul.inventory_management.security.SecurityConfig;
import com.paul.inventory_management.service.AppUserService;
import com.paul.inventory_management.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final AppUserService appUserService;
	
	private final JwtService jwtService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDTO loginRequest) {
		
		UsernamePasswordAuthenticationToken authRequest = 
				new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
	
		Authentication authentication = authenticationManager.authenticate(authRequest);		
		
		String username = authentication.getName();
		
		AppUserResponseDTO user = appUserService.findByEmail(username);
		
		String token = jwtService.generateToken(user.id().toString());
		
		return token;
		
	}

}
