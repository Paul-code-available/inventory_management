package dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record CategoryRequestDTO(
		
		@NotNull(message = "El nombre es obligatorio")
		String name,
		
		String description
		
		) {

}
