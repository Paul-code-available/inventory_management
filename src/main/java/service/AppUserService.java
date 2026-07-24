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
	
	public AppUserResponseDTO create(AppUserCreateDTO dto) throws AttributeNotFoundException {
		
		if (appUserRepository.existsByEmailAndStatusActive(dto.email())) {
			// retornar una excepcion 
		}
		
		Role role = roleRepository.findById(dto.role()).orElseThrow(() -> new AttributeNotFoundException());
		
		AppUser user = appUserMapper.toEntity(dto, role);
		
		AppUser savedUser = appUserRepository.save(user);
		
		return appUserMapper.toDTO(savedUser);
		
	}
	
	public AppUserResponseDTO findById(Long id) throws Exception {
		
		AppUser user = appUserRepository.findById(id).orElseThrow(() -> new Exception());
		
		return appUserMapper.toDTO(user);
		
	}
	
	public void delete(Long id) throws Exception {
		
		AppUser user = appUserRepository.findById(id).orElseThrow(() -> new Exception());

		user.setStatus(Status.INACTIVE);
		
		appUserRepository.save(user);
		
	}
	
	public AppUserResponseDTO update(Long id, AppUserUpdateDTO dto) throws Exception {
		
		AppUser user = appUserRepository.findById(id).orElseThrow(() -> new Exception());
		
		if (appUserRepository.existsByEmailAndIdNot(id, dto.email())) {
			// retornar excepcion 
		}
		
		if (appUserRepository.existByPhoneAndIdNot(id, dto.phone())) {
			// retornar excepcion
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
			
			Role newRole = roleRepository.findById(dto.role()).orElseThrow(() -> new Exception());
			
			user.setRole(newRole);	
		}
		
		AppUser updatedUser = appUserRepository.save(user);
		
		return appUserMapper.toDTO(updatedUser);

	}
	

}
