package dto;

import jakarta.validation.constraints.NotNull;

public record RoleRequestDTO(
		
		@NotNull(message = "El nombre es obligatorio")
		String name,
		String description
		
		) {

}
