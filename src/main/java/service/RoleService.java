package service;

import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import dto.RoleRequestDTO;
import dto.RoleResponseDTO;
import entity.Role;
import exception.BusinessRuleException;
import exception.ResourceAlreadyExistsException;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.RoleMapper;
import repository.ProductRepository;
import repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
	
	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;
	
	public RoleResponseDTO create(RoleRequestDTO dto) {
		
		if (roleRepository.existsByName(dto.name())) {
			throw new BusinessRuleException("Already exists a role with name " + dto.name());
		}
		
		Role role = roleMapper.toEntity(dto);
		
		Role savedRole = roleRepository.save(role);
		
		return roleMapper.toDTO(savedRole);
		
	}
	
	public RoleResponseDTO update(Long id, RoleRequestDTO dto) {
		
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
