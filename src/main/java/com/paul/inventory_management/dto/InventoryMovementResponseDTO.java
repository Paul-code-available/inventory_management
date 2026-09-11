package com.paul.inventory_management.dto;

import java.time.LocalDateTime;

import com.paul.inventory_management.enums.MovementType;

public record InventoryMovementResponseDTO(
		
		Long id,
		MovementType movementType,
		Integer quantity,
		Integer stockBefore,
		Integer stockAfter,
		LocalDateTime createdAt,
		ProductResponseDTO product
		
		) {

}
