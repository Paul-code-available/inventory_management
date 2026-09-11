package com.paul.inventory_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paul.inventory_management.dto.RoleCreateDTO;
import com.paul.inventory_management.dto.RoleResponseDTO;
import com.paul.inventory_management.dto.RoleUpdateDTO;
import com.paul.inventory_management.service.RoleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(
            @Valid @RequestBody RoleCreateDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> findById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                roleService.findById(id)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody RoleUpdateDTO dto) {

        return ResponseEntity.ok(
                roleService.update(id, dto)
        );
    }
}