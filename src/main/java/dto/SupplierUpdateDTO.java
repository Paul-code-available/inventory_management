package dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SupplierUpdateDTO(
		
		String companyName,
		
		String contactName, 
		
		String email,
		
		String phone,
		
		String taxId,
		
		String address
		
		) {

}
