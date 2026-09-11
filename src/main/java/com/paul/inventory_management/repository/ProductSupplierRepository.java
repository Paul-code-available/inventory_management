package com.paul.inventory_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paul.inventory_management.dto.ProductSupplierResponseDTO;
import com.paul.inventory_management.entity.ProductSupplier;

public interface ProductSupplierRepository extends JpaRepository<ProductSupplier, Long> {

	boolean existsByProductIdAndSupplierId(Long productId, Long supplierId);
	
	List <ProductSupplier> findByProductId(Long productId); 
	
	List <ProductSupplier> findBySupplierId(Long supplierId);
	
}
