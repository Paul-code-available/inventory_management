package com.paul.inventory_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierCreateDTO(
		
		@NotNull(message = "El nombre de la empresa es obligatoria")
		@NotBlank(message = "El nombre de la empresa es obligatoria")
		String companyName,
		
		@NotNull(message = "El nombre del contacto es obligatorio")
		@NotBlank(message = "El nombre del contacto es obligatorio")
		String contactName, 
		
		@NotBlank(message = "El email es obligatorio")
		@NotNull
		@Email
		String email,
		
		String phone,
		
		@NotNull(message = "El tax es obligatorio")
		@NotBlank(message = "Tax is required")
		String taxId,
		
		String address
		
		) {

}
