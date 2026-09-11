package com.paul.inventory_management.mapper;

import org.springframework.stereotype.Component;

import com.paul.inventory_management.dto.ProductResponseDTO;
import com.paul.inventory_management.dto.ProductSupplierCreateDTO;
import com.paul.inventory_management.dto.ProductSupplierResponseDTO;
import com.paul.inventory_management.entity.Product;
import com.paul.inventory_management.entity.ProductSupplier;
import com.paul.inventory_management.entity.Supplier;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductSupplierMapper {
	
	ProductMapper productMapper;
	SupplierMapper supplierMapper;
	
	public ProductSupplierResponseDTO toDTO(ProductSupplier productSupplier) {
		
		return new ProductSupplierResponseDTO(
				productSupplier.getId(),
				productSupplier.getSupplierPrice(),
				productSupplier.getLeadTimeDays(),
				productMapper.toDTO(productSupplier.getProduct()),
				supplierMapper.toDTO(productSupplier.getSupplier())
				);
		
	}
	
	public ProductSupplier toEntity(ProductSupplierCreateDTO dto, Product product, Supplier supplier) {
		
		return ProductSupplier.builder()
				.supplierPrice(dto.supplierPrice())
				.supplierSku(dto.supplierSku())
				.leadTimeDays(dto.leadTimeDays())
				.product(product)
				.supplier(supplier)
				.build();
		
	}

}
