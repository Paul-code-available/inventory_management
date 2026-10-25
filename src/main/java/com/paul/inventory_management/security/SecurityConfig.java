package com.paul.inventory_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
		
		provider.setPasswordEncoder(passwordEncoder);
		
		return new ProviderManager(provider);
		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,
	                                               JwtAuthenticationFilter jwtFilter) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(authorize -> authorize
	            .requestMatchers(
	                "/auth/login",
	                "/swagger-ui/**",
	                "/swagger-ui.html",
	                "/v3/api-docs/**"
	            ).permitAll()
	            .anyRequest().authenticated()
	        )
	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
	        .exceptionHandling(e -> e.authenticationEntryPoint(
	                new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)));

	    return http.build();
	}
	
}
