package com.paul.inventory_management.mapper;

import org.springframework.stereotype.Component;

import com.paul.inventory_management.dto.AppUserCreateDTO;
import com.paul.inventory_management.dto.AppUserResponseDTO;
import com.paul.inventory_management.dto.RoleResponseDTO;
import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.entity.Role;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppUserMapper {
	
	public AppUserResponseDTO toDTO(AppUser user) {

		return new AppUserResponseDTO(
				user.getId(), 
				user.getFirstName(), 
				user.getLastName(), 
				user.getEmail(), 
				user.getPhone(), 
				new RoleResponseDTO(
						user.getRole().getId(),
						user.getRole().getName()
						)
				);
	}
	
	public AppUser toEntity(AppUserCreateDTO dto, Role role) {
		
		return AppUser.builder()
				.firstName(dto.firstName())
				.lastName(dto.lastName())
				.email(dto.email())
				.phone(dto.phone())
				.password(dto.password())
				.role(role)
				.build();
		
	}

}
