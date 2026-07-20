package dto;

import java.time.LocalDateTime;

import enums.MovementType;

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
