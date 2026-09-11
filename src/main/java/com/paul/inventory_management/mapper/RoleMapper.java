package com.paul.inventory_management.mapper;

import org.springframework.stereotype.Component;

import com.paul.inventory_management.dto.RoleCreateDTO;
import com.paul.inventory_management.dto.RoleResponseDTO;
import com.paul.inventory_management.entity.Role;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleMapper {
	
	public RoleResponseDTO toDTO(Role role) {
		
		return new RoleResponseDTO(
				role.getId(),
				role.getName()
				);
	}
	
	public Role toEntity(RoleCreateDTO dto) {
		
		return Role.builder()
				.name(dto.name())
				.description(dto.description())
				.build();
	}

}
