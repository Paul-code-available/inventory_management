package com.paul.inventory_management.dto;

import com.paul.inventory_management.enums.MovementType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InventoryMovementCreateDTO(
	
		
		MovementType movementType,
		
		@NotNull(message = "La cantidad es obligatoria")
		Integer quantity, 
		
		@NotNull(message = "El producto es obligatorio")
		Long productId
		
		) {
	
}
