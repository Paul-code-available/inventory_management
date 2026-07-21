package mapper;

import dto.RoleRequestDTO;
import dto.RoleResponseDTO;
import entity.Role;

public class RoleMapper {
	
	public RoleResponseDTO toDTO(Role role) {
		
		return new RoleResponseDTO(
				role.getId(),
				role.getName()
				);
	}
	
	public Role toEntity(RoleRequestDTO dto) {
		
		return Role.builder()
				.name(dto.name())
				.description(dto.description())
				.build();
	}

}
