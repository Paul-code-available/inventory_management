package com.paul.inventory_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
		
		@NotEmpty
		@Email
		String email,

		@NotEmpty
		String password
		
		) {

}
