package dto;

public record AppUserUpdateDTO(
		
		String firstName,
		String lastName,
		String email,
		String phone,
		String password,
		Long role
		
		) {

}
