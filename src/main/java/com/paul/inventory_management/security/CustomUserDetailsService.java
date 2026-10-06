package com.paul.inventory_management.security;

import java.beans.Encoder;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	private final AppUserRepository appUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		
		// buscar usuario
		AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email " + email));
		
		// convertir AppUser -> UserDetails
		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.authorities(new SimpleGrantedAuthority(user.getRole().getName()))
				.build();
		
		
		
	}
	
	

}
