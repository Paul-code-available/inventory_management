package mapper;

import org.springframework.stereotype.Component;

import dto.CategoryResponseDTO;
import dto.InventoryMovementRequestDTO;
import dto.InventoryMovementResponseDTO;
import dto.ProductResponseDTO;
import entity.InventoryMovement;
import entity.Product;
import enums.MovementType;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryMovementMapper {
	
	private final ProductMapper productMapper;
	
	public InventoryMovementResponseDTO toDTO(InventoryMovement inventoryMovement) {
		
		return new InventoryMovementResponseDTO(
				
				inventoryMovement.getId(),
				inventoryMovement.getMovementType(),
				inventoryMovement.getQuantity(),
				inventoryMovement.getStockBefore(),
				inventoryMovement.getStockAfter(),
				inventoryMovement.getCreatedAt(),
				productMapper.toDTO(inventoryMovement.getProduct())
					
				);
		
	}
	
	public InventoryMovement toEntity(InventoryMovementRequestDTO dto, Product product) {
		
		return InventoryMovement.builder()
				.movementType(dto.movementType())
				.quantity(dto.quantity())
				.product(product)
				.build();
	}

}
