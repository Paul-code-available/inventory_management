package com.paul.inventory_management.service;

import java.io.ObjectInputStream.GetField;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.paul.inventory_management.dto.InventoryMovementCreateDTO;
import com.paul.inventory_management.dto.InventoryMovementResponseDTO;
import com.paul.inventory_management.entity.AppUser;
import com.paul.inventory_management.entity.InventoryMovement;
import com.paul.inventory_management.entity.Product;
import com.paul.inventory_management.enums.MovementType;
import com.paul.inventory_management.enums.Status;
import com.paul.inventory_management.exception.BusinessRuleException;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.mapper.AppUserMapper;
import com.paul.inventory_management.mapper.InventoryMovementMapper;
import com.paul.inventory_management.repository.AppUserRepository;
import com.paul.inventory_management.repository.InventoryMovementRepository;
import com.paul.inventory_management.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryMovementService {

	private final InventoryMovementRepository inventoryMovementRepository;
	private final InventoryMovementMapper inventoryMovementMapper;
	
	private final ProductRepository productRepository;
	private final AppUserRepository appUserRepository;	

	@Transactional
	public InventoryMovementResponseDTO create(InventoryMovementCreateDTO dto, String email) {
				
		Product product = productRepository.findById(dto.productId()).orElseThrow(() 
				-> new ResourceNotFoundException("Product not found with id " + dto.productId())); 
		
		if (product.getStatus() == Status.INACTIVE) {
			throw new BusinessRuleException("Product " + product.getName() + " is inactive");
		}
		
		InventoryMovement inventoryMovement = inventoryMovementMapper.toEntity(dto, product);
		
		AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email " + email));
		
		if (user.getStatus() == Status.INACTIVE) {
			throw new BusinessRuleException("User " + user.getFirstName() + " is inactive");
		}
		
		inventoryMovement.setUser(user);
		
		if (inventoryMovement.getMovementType() == null) {
			
			throw new BusinessRuleException("Movement type is required");
			
		}
		
		if (inventoryMovement.getMovementType() == MovementType.EXIT) {
			
			if (inventoryMovement.getQuantity() <= 0) {
				
				throw new BusinessRuleException("Quantity cannot be 0 o negative");
				
			}
			
			if (inventoryMovement.getQuantity() > product.getStock()) {
				
				throw new BusinessRuleException("Quantity cannot be greater than stock");

			}
			
			inventoryMovement.setStockBefore(product.getStock());
			
			product.setStock(product.getStock() - inventoryMovement.getQuantity());
			
			inventoryMovement.setStockAfter(product.getStock());

		} 
		
		if (inventoryMovement.getMovementType() == MovementType.RETURN || inventoryMovement.getMovementType() == MovementType.ENTRY) {
			
			if (inventoryMovement.getQuantity() <= 0) {
				
				throw new BusinessRuleException("Quantity cannot be cero o negative");
			
			}
			
			inventoryMovement.setStockBefore(product.getStock());
			
			product.setStock(product.getStock() + inventoryMovement.getQuantity());
			
			
			
			inventoryMovement.setStockAfter(product.getStock());
			
		} 
		
		if (inventoryMovement.getMovementType() == MovementType.ADJUSTMENT) {
			
			if (inventoryMovement.getQuantity() == 0) {
				
				throw new BusinessRuleException("Quantity cannot be cero");
			
			}
			
			inventoryMovement.setStockBefore(product.getStock());
			
			product.setStock(product.getStock() + inventoryMovement.getQuantity());
			
			if (product.getStock() < 0) {
				throw new BusinessRuleException("Product stock can not be negative");
			}
			
			inventoryMovement.setStockAfter(product.getStock());
			
		} 
		
		if (product.getStock() <= product.getMinimumStock()) {
			
			log.warn("Product stock is low");
			
		}
		
		inventoryMovement.setUnitCost(product.getPurchasePrice());
		
		productRepository.save(product);

		InventoryMovement inventoryMovementSaved = inventoryMovementRepository.save(inventoryMovement);
		
		return inventoryMovementMapper.toDTO(inventoryMovementSaved);
	
	}
	
	public InventoryMovementResponseDTO findById(Long id) {
		
		InventoryMovement inventoryMovement = inventoryMovementRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Inventory movement not found with id " + id));
		
		return inventoryMovementMapper.toDTO(inventoryMovement);
		
	}
	
	public List<InventoryMovementResponseDTO> findAll() {
		
		List<InventoryMovementResponseDTO> listFindAll = new ArrayList<>();
		
		for (InventoryMovement inventoryMovement : inventoryMovementRepository.findAll()) {
	
			listFindAll.add(inventoryMovementMapper.toDTO(inventoryMovement));
			
		}
		
		return listFindAll;
		
	}
	
	public List<InventoryMovementResponseDTO> findByProduct(Long productId) {
		
		List<InventoryMovementResponseDTO> listFindByProduct = new ArrayList<>();
		
		for (InventoryMovement inventoryMovement : inventoryMovementRepository.findByProduct(productId)) {
			
			listFindByProduct.add(inventoryMovementMapper.toDTO(inventoryMovement));
			
		}
		
		return listFindByProduct;

	}
	
	public List<InventoryMovementResponseDTO> findByUser(Long userId) {
		
		List<InventoryMovementResponseDTO> listFindByUser = new ArrayList<>();
		
		for (InventoryMovement inventoryMovement : inventoryMovementRepository.findByUser(userId)) {
			
			listFindByUser.add(inventoryMovementMapper.toDTO(inventoryMovement));
			
		}
		
		return listFindByUser;
		
	}
	
}
