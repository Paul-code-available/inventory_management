package com.paul.inventory_management.dto;

public record SupplierResponseDTO(
		
		Long id,
		String companyName,
		String contactName,
		String email,
		String taxId
		
		) {
	
}
