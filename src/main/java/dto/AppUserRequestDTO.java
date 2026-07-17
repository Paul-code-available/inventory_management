package dto;

public record AppUserRequestDTO(
		
		String firstName,
		String lastName,
		String email,
		String phone,
		String password,
		Long role
		
		){
	
}
