package com.paul.inventory_management.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;

public record CategoryCreateDTO(
		
		@NotNull(message = "El nombre es obligatorio")
		String name,
		
		String description
		
		) {

}
