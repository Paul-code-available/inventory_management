package com.paul.inventory_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paul.inventory_management.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	boolean existsByEmail(String email);
	
	boolean existsByTaxId(String taxId);
	
	boolean existsByTaxIdAndIdNot(String taxId, Long id);
	
	boolean existsByEmailAndIdNot(String email, Long id);
	
}
