package dto;

public record AppUserResponseDTO(
		
		Long id,
		String firstName,
		String lastName,
		String email,
		String phone,
		RoleResponseDTO role
		
		) {

}
