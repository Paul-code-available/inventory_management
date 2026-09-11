package com.paul.inventory_management.service;

import org.springframework.stereotype.Service;

import com.paul.inventory_management.dto.ProductResponseDTO;
import com.paul.inventory_management.dto.SupplierCreateDTO;
import com.paul.inventory_management.dto.SupplierResponseDTO;
import com.paul.inventory_management.dto.SupplierUpdateDTO;
import com.paul.inventory_management.entity.Supplier;
import com.paul.inventory_management.enums.Status;
import com.paul.inventory_management.exception.BusinessRuleException;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.mapper.SupplierMapper;
import com.paul.inventory_management.repository.ProductRepository;
import com.paul.inventory_management.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierService {

	private final SupplierRepository supplierRepositoy;
	private final SupplierMapper supplierMapper;
	
	public SupplierResponseDTO create(SupplierCreateDTO dto) {
		
		if (supplierRepositoy.existsByEmail(dto.email())) {
			throw new BusinessRuleException("Email already exists");	
		}
		
		if (supplierRepositoy.existsByTaxId(dto.taxId())) {
			throw new BusinessRuleException("Tax already exists");
		}
		
		Supplier supplier = supplierMapper.toEntity(dto);
		
		supplier.setStatus(Status.ACTIVE);
		
		Supplier savedSupplier = supplierRepositoy.save(supplier);
		
		return supplierMapper.toDTO(savedSupplier);
		
	}
	
	public SupplierResponseDTO findById(Long id) {
		
		Supplier supplier = supplierRepositoy.findById(id).orElseThrow(() 
				-> new ResourceNotFoundException("Supplier not found with id " + id));

		return supplierMapper.toDTO(supplier);
		
	}
	
	public void deactivate(Long id) {
		
		Supplier supplier = supplierRepositoy.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Supplier not found with id " + id));
		
		supplier.setStatus(Status.INACTIVE);
		
		supplierRepositoy.save(supplier);
		
	}
	
	public SupplierResponseDTO update(Long id, SupplierUpdateDTO dto) {
		
		Supplier supplier = supplierRepositoy.findById(id).orElseThrow(() 
				-> new ResourceNotFoundException("Supplier not found with id " + id));
		
		if (dto.companyName() != null) {
			supplier.setCompanyName(dto.companyName());
		}
		
		if (dto.contactName() != null) {
			supplier.setContactName(dto.contactName());
		}
		
		if (dto.email() != null) {
			
			if (supplierRepositoy.existsByEmailAndIdNot(dto.email(), id)) {
				throw new BusinessRuleException("Email already exists");
			}
			
			supplier.setEmail(dto.email());
		}
		
		if (dto.phone() != null) {
			supplier.setPhone(dto.phone());
		}
		
		if (dto.taxId() != null) {
			
			if (supplierRepositoy.existsByTaxIdAndIdNot(dto.taxId(), id)) {
				throw new BusinessRuleException("Tax id already exists");
			}
			
			supplier.setTaxId(dto.taxId());
		}
		
		if (dto.address() != null) {
			supplier.setAddress(dto.address());
		}
		
		Supplier savedSupplier = supplierRepositoy.save(supplier);
		
		return supplierMapper.toDTO(savedSupplier);
		
	}
	
	
}
