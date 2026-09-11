package com.paul.inventory_management.dto;

import jakarta.validation.constraints.NotNull;

public record CategoryUpdateDTO(
		
		String name,
		
		String description
		
		) {

}
