package com.paul.inventory_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paul.inventory_management.dto.InventoryMovementCreateDTO;
import com.paul.inventory_management.dto.InventoryMovementResponseDTO;
import com.paul.inventory_management.service.InventoryMovementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory-movements")
@RequiredArgsConstructor
public class InventoryMovementController {
	
	private final InventoryMovementService inventoryMovementService;
	
	@PostMapping
	public ResponseEntity<InventoryMovementResponseDTO> create(@Valid @RequestBody InventoryMovementCreateDTO dto) {
		
		InventoryMovementResponseDTO response = inventoryMovementService.create(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<InventoryMovementResponseDTO> findById(@PathVariable Long id) {
		
		return ResponseEntity.ok(inventoryMovementService.findById(id));
		
	}
	
	@GetMapping
	public ResponseEntity<List<InventoryMovementResponseDTO>> findAll() {
		
		return ResponseEntity.status(HttpStatus.OK).body(inventoryMovementService.findAll());
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<List<InventoryMovementResponseDTO>> findByProduct(@PathVariable Long id) {
		
		return ResponseEntity.ok(inventoryMovementService.findByProduct(id));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<InventoryMovementResponseDTO>> findByUser(@PathVariable Long id) {
		
		return ResponseEntity.ok(inventoryMovementService.findByUser(id));
	}
	

}
