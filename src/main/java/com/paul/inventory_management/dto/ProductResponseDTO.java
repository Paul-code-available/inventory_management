package com.paul.inventory_management.dto;

import java.math.BigDecimal;

public record ProductResponseDTO(
		
		Long id,
		String name,
		BigDecimal salePrice,
		Integer stock,
		CategoryResponseDTO category
		
		) {

}
