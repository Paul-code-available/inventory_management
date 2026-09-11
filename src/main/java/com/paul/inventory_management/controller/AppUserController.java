package com.paul.inventory_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paul.inventory_management.dto.AppUserCreateDTO;
import com.paul.inventory_management.dto.AppUserResponseDTO;
import com.paul.inventory_management.dto.AppUserUpdateDTO;
import com.paul.inventory_management.service.AppUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/appusers")
@RequiredArgsConstructor
public class AppUserController {

	private final AppUserService appUserService;
	
	@PostMapping
	public ResponseEntity<AppUserResponseDTO> create(@Valid @RequestBody AppUserCreateDTO dto) {
		
		AppUserResponseDTO response = appUserService.create(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUserResponseDTO> findById(@PathVariable Long id) {
		
		AppUserResponseDTO response = appUserService.findById(id);
		
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> deactivate(@PathVariable Long id) {
		
		appUserService.delete(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<AppUserResponseDTO> update(@PathVariable Long id, @RequestBody AppUserUpdateDTO dto) {
		
		AppUserResponseDTO response = appUserService.update(id, dto);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
}
