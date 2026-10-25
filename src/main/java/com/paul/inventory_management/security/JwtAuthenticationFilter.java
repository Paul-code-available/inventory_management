package com.paul.inventory_management.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.paul.inventory_management.dto.AppUserCreateDTO;
import com.paul.inventory_management.dto.AppUserResponseDTO;
import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.entity.Role;
import com.paul.inventory_management.enums.Status;
import com.paul.inventory_management.mapper.AppUserMapper;
import com.paul.inventory_management.service.AppUserService;
import com.paul.inventory_management.service.JwtService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	private final JwtService jwtService;
	private final AppUserService appUserService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			 filterChain.doFilter(request, response);
			 return;
		}
		
		String token = authHeader.substring(7);
		
		try {
			
			if (jwtService.validateToken(token)
					&& SecurityContextHolder.getContext().getAuthentication() == null) {
				
				Long userId = jwtService.extractUserId(token);
				AppUser user = appUserService.findEntityById(userId);		
				
				if (user.getStatus() == Status.ACTIVE) {
					
					List<GrantedAuthority> authorities =
							List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName()));
		
					
					UsernamePasswordAuthenticationToken authentication = 
							new UsernamePasswordAuthenticationToken(user.getEmail(), null, authorities);
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
		
				}
		
			}
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
		}
		
		filterChain.doFilter(request, response);
		
	}

}
