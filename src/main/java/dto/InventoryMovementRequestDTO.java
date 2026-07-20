package dto;

import enums.MovementType;

public record InventoryMovementRequestDTO(
	
		MovementType movementType,
		Integer quantity, 
		Long productId
		
		) {
	
}
