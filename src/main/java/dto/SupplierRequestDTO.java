package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierRequestDTO(
		
		@NotNull(message = "El nombre de la empresa es obligatoria")
		String companyName,
		
		@NotNull(message = "El nombre del contacto es obligatorio")
		String contactName, 
		
		@NotBlank(message = "El email es obligatorio")
		String email,
		
		String phone,
		
		@NotNull(message = "El tax es obligatorio")
		String taxId,
		
		String address
		
		) {

}
