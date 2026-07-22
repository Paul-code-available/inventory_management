package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppUserRequestDTO(
		
		@NotNull(message = "El nombre es obligatorio")
		String firstName,
		
		@NotNull(message = "El apellido es obligatorio")
		String lastName,
		
		@NotBlank(message = "El email es obligatorio")
		String email,
		String phone,
		
		@NotBlank(message = "La contraseña es obligatoria")
		String password,
		
		@NotNull(message = "El rol es obligatorio")
		Long role
		
		){	
	
}
