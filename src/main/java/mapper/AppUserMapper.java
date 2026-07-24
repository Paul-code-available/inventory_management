package mapper;

import org.springframework.stereotype.Component;

import dto.AppUserCreateDTO;
import dto.AppUserResponseDTO;
import dto.RoleResponseDTO;
import entity.AppUser;
import entity.Role;

@Component
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
