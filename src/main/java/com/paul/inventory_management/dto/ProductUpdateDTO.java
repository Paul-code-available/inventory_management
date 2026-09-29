package com.paul.inventory_management.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductUpdateDTO(
		
		String sku,
		
		String name,
		
		String brand,
		
		String description,
		
		@Positive(message = "Purchase price have to be greater than zero")
		BigDecimal purchasePrice,
		
		@Positive(message = "Sale price have to be greater than zero")
		BigDecimal salePrice,
		
		@Positive(message = "Stock have to be greater than zero")
		int stock,
		
		@Positive(message = "Minimum stock have to be greater than zero or zero")
		Integer minimumStock,
		
		String imgUrl,
		
		Long categoryId
		
		) {

}
