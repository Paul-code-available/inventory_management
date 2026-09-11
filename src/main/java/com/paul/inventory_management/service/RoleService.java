package com.paul.inventory_management.service;

import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import com.paul.inventory_management.dto.RoleCreateDTO;
import com.paul.inventory_management.dto.RoleResponseDTO;
import com.paul.inventory_management.dto.RoleUpdateDTO;
import com.paul.inventory_management.entity.Role;
import com.paul.inventory_management.exception.BusinessRuleException;
import com.paul.inventory_management.exception.ResourceAlreadyExistsException;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.mapper.RoleMapper;
import com.paul.inventory_management.repository.ProductRepository;
import com.paul.inventory_management.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;
	
	public RoleResponseDTO create(RoleCreateDTO dto) {
		
		if (roleRepository.existsByName(dto.name())) {
			throw new BusinessRuleException("Already exists a role with name " + dto.name());
		}
		
		Role role = roleMapper.toEntity(dto);
		
		Role savedRole = roleRepository.save(role);
		
		return roleMapper.toDTO(savedRole);
		
	}
	
	public RoleResponseDTO update(Long id, RoleUpdateDTO dto) {
		
		Role role = roleRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Role not found with id " + id));
		
		if (dto.name() != null) {
			
			if (roleRepository.existsByNameAndIdNot(dto.name(), id)) {
				throw new ResourceAlreadyExistsException("Role already exists with name " + dto.name());
			}
			
			role.setName(dto.name());
			
		}
		
		if (dto.description() != null) {
			
			role.setDescription(dto.description());
			
		}
		
		Role savedRole = roleRepository.save(role);
		
		return roleMapper.toDTO(savedRole);
		
	}
	
	public RoleResponseDTO findById(Long id) {
		
		Role role = roleRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Role not found with id " + id));
		
		return roleMapper.toDTO(role);
		
	}
	
	
}
