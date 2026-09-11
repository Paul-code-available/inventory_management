package com.paul.inventory_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paul.inventory_management.dto.SupplierCreateDTO;
import com.paul.inventory_management.dto.SupplierResponseDTO;
import com.paul.inventory_management.dto.SupplierUpdateDTO;
import com.paul.inventory_management.service.SupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> create(
            @Valid @RequestBody SupplierCreateDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(supplierService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                supplierService.findById(id)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody SupplierUpdateDTO dto) {

        return ResponseEntity.ok(
                supplierService.update(id, dto)
        );
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(
            @PathVariable Long id) {

        supplierService.deactivate(id);

        return ResponseEntity.noContent().build();
    }
}