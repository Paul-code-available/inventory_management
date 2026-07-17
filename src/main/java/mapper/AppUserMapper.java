package mapper;

import dto.AppUserResponseDTO;
import dto.RoleResponseDTO;
import entity.AppUser;

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

}
