package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.ProductCreateDTO;
import dto.ProductResponseDTO;
import dto.ProductUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import service.ProductService;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // CREATE
    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(
            @Valid @RequestBody ProductCreateDTO dto) {

        ProductResponseDTO response = productService.create(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateDTO dto) {

        ProductResponseDTO response = productService.update(id, dto);

        return ResponseEntity.ok(response);
    }

    // DEACTIVATE
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(
            @PathVariable Long id) {

        productService.deactivate(id);

        return ResponseEntity.noContent().build();
    }

    // FIND BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(
            @PathVariable Long id) {

        ProductResponseDTO response = productService.findById(id);

        return ResponseEntity.ok(response);
    }

    // FIND BY SKU
    @GetMapping("/sku/{sku}")
    public ResponseEntity<ProductResponseDTO> findBySku(
            @PathVariable String sku) {

        ProductResponseDTO response = productService.findBySku(sku);

        return ResponseEntity.ok(response);
    }
}