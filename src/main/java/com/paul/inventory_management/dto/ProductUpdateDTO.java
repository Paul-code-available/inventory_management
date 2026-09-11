package com.paul.inventory_management.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductUpdateDTO(
		
		String sku,
		
		String name,
		
		String brand,
		
		String description,
		
		@Positive
		BigDecimal purchasePrice,
		
		@Positive
		BigDecimal salePrice,
		
		@Positive
		int stock,
		
		@PositiveOrZero
		Integer minimumStock,
		
		String imgUrl,
		
		Long categoryId
		
		) {

}
