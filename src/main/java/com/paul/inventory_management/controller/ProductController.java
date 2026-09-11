package com.paul.inventory_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paul.inventory_management.dto.ProductCreateDTO;
import com.paul.inventory_management.dto.ProductResponseDTO;
import com.paul.inventory_management.dto.ProductUpdateDTO;
import com.paul.inventory_management.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @Valid @RequestBody ProductCreateDTO dto) {

        ProductResponseDTO response = productService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO dto) {

        ProductResponseDTO response = productService.update(id, dto);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(
            @PathVariable Long id) {

        productService.deactivate(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(
            @PathVariable Long id) {

        ProductResponseDTO response = productService.findById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponseDTO> findBySku(
            @PathVariable String sku) {

        ProductResponseDTO response = productService.findBySku(sku);

        return ResponseEntity.ok(response);
    }
}