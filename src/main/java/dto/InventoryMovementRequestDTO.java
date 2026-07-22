package dto;

import enums.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InventoryMovementRequestDTO(
	
		
		MovementType movementType,
		
		@NotNull(message = "La cantidad es obligatoria")
		@Positive(message = "La cantidad debe ser mayor a 0")
		Integer quantity, 
		
		@NotNull(message = "El producto es obligatorio")
		Long productId
		
		) {
	
}
