package service;

import javax.management.AttributeNotFoundException;

import org.springframework.stereotype.Service;

import dto.AppUserCreateDTO;
import dto.AppUserResponseDTO;
import dto.AppUserUpdateDTO;
import entity.AppUser;
import entity.Category;
import entity.Role;
import enums.Status;
import exception.ResourceAlreadyExistsException;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.AppUserMapper;
import repository.AppUserRepository;
import repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class AppUserService {
	
	private final AppUserRepository appUserRepository;
	private final RoleRepository roleRepository;
	private final AppUserMapper appUserMapper;
	
	public AppUserResponseDTO create(AppUserCreateDTO dto)  {
		
		if (appUserRepository.existsByEmailAndStatusActive(dto.email())) {
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
			if (appUserRepository.existByPhoneAndIdNot(dto.phone(), id)) {
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
		
		if (dto.password() != null) { // encriptar contraseña
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
