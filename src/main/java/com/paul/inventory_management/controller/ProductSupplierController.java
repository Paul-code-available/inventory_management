package com.paul.inventory_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paul.inventory_management.dto.ProductSupplierCreateDTO;
import com.paul.inventory_management.dto.ProductSupplierResponseDTO;
import com.paul.inventory_management.dto.ProductSupplierUpdateDTO;
import com.paul.inventory_management.service.ProductSupplierService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product-suppliers")
@RequiredArgsConstructor
public class ProductSupplierController {

    private final ProductSupplierService productSupplierService;

    @PostMapping
    public ResponseEntity<ProductSupplierResponseDTO> create(
            @Valid @RequestBody ProductSupplierCreateDTO dto) {

        ProductSupplierResponseDTO response =
                productSupplierService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductSupplierResponseDTO>> findAll() {

        return ResponseEntity.ok(
                productSupplierService.findAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSupplierResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                productSupplierService.findById(id)
        );
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ProductSupplierResponseDTO>> findByProduct(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productSupplierService.findByProduct(productId)
        );
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductSupplierResponseDTO>> findBySupplier(
            @PathVariable Long supplierId) {

        return ResponseEntity.ok(
                productSupplierService.findBySupplier(supplierId)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductSupplierResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductSupplierUpdateDTO dto) {

        return ResponseEntity.ok(
                productSupplierService.update(id, dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivate(
            @PathVariable Long id) {

        productSupplierService.deactivate(id);

        return ResponseEntity.noContent().build();
    }
}