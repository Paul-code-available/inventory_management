package com.paul.inventory_management.service;

import javax.management.AttributeNotFoundException;

import org.springframework.stereotype.Service;

import com.paul.inventory_management.dto.AppUserCreateDTO;
import com.paul.inventory_management.dto.AppUserResponseDTO;
import com.paul.inventory_management.dto.AppUserUpdateDTO;
import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.entity.Category;
import com.paul.inventory_management.entity.Role;
import com.paul.inventory_management.enums.Status;
import com.paul.inventory_management.exception.ResourceAlreadyExistsException;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.mapper.AppUserMapper;
import com.paul.inventory_management.repository.AppUserRepository;
import com.paul.inventory_management.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService {
	
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final AppUserMapper appUserMapper;
	
	// TODO: encrypt password
	public AppUserResponseDTO create(AppUserCreateDTO dto)  {
		
		if (appUserRepository.existsByEmailAndStatus(dto.email(), Status.ACTIVE)) {
			throw new ResourceAlreadyExistsException("Email can not be duplicated");
		}
		
		Role role = roleRepository.findById(dto.role()).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + dto.role()));
		
		AppUser user = appUserMapper.toEntity(dto, role);
		
		user.setStatus(Status.ACTIVE);
		
		AppUser savedUser = appUserRepository.save(user);
		
		return appUserMapper.toDTO(savedUser);
		
	}
	
	public AppUserResponseDTO findById(Long id) {
		
		AppUser user = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		
		return appUserMapper.toDTO(user);
		
	}
	
	public void delete(Long id) {
		
		AppUser user = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id)); ;

		user.setStatus(Status.INACTIVE);
		
		appUserRepository.save(user);
		
	}
	
	public AppUserResponseDTO update(Long id, AppUserUpdateDTO dto) {
		
		AppUser user = appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
		
		
		if (dto.email() != null) {
			if (appUserRepository.existsByEmailAndIdNot(dto.email(), id)) {
				throw new ResourceAlreadyExistsException("User already exists with email " + dto.email());
			}
		}
		
		if (dto.phone() != null) {
			if (appUserRepository.existsByPhoneAndIdNot(dto.phone(), id)) {
				throw new ResourceAlreadyExistsException("User already exists with phone " + dto.phone());
			}
		}
		
		if (dto.firstName() != null) {
			user.setFirstName(dto.firstName());
		}
		
		if (dto.lastName() != null) {
			user.setLastName(dto.lastName());
		}
		
		if (dto.email() != null) {
			user.setEmail(dto.email());
		}
		
		if (dto.phone() != null) {
			user.setPhone(dto.phone());
		}
		
		if (dto.password() != null) { 
			user.setPassword(dto.password());
		}
		
		if (dto.role() != null) {
			
			Role newRole = roleRepository.findById(dto.role()).orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + dto.role()));
			
			user.setRole(newRole);	
		}
		
		AppUser updatedUser = appUserRepository.save(user);
		
		return appUserMapper.toDTO(updatedUser);

	}
	

}
