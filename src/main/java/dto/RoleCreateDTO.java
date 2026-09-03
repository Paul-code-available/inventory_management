package dto;

import jakarta.validation.constraints.NotNull;

public record RoleCreateDTO(
		
		@NotNull(message = "El nombre es obligatorio")
		String name,
		String description
		
		) {

}
