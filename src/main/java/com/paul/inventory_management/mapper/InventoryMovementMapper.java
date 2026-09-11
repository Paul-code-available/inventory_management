package com.paul.inventory_management.mapper;

import org.springframework.stereotype.Component;

import com.paul.inventory_management.dto.CategoryResponseDTO;
import com.paul.inventory_management.dto.InventoryMovementCreateDTO;
import com.paul.inventory_management.dto.InventoryMovementResponseDTO;
import com.paul.inventory_management.dto.ProductResponseDTO;
import com.paul.inventory_management.entity.InventoryMovement;
import com.paul.inventory_management.entity.Product;
import com.paul.inventory_management.enums.MovementType;

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
	
	public InventoryMovement toEntity(InventoryMovementCreateDTO dto, Product product) {
		
		return InventoryMovement.builder()
				.movementType(dto.movementType())
				.quantity(dto.quantity())
				.product(product)
				.build();
	}

}
