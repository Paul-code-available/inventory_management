package com.paul.inventory_management.mapper;

import org.springframework.stereotype.Component;

import com.paul.inventory_management.dto.SupplierCreateDTO;
import com.paul.inventory_management.dto.SupplierResponseDTO;
import com.paul.inventory_management.entity.Supplier;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SupplierMapper {
	
	public SupplierResponseDTO toDTO(Supplier supplier) {
		
		return new SupplierResponseDTO(
				supplier.getId(),
				supplier.getCompanyName(),
				supplier.getContactName(),
				supplier.getEmail(),
				supplier.getTaxId()
				);
		
	}
	
	public Supplier toEntity(SupplierCreateDTO dto) {
		
		return Supplier.builder()
				.companyName(dto.companyName())
				.contactName(dto.contactName())
				.email(dto.email())
				.phone(dto.phone())
				.taxId(dto.taxId())
				.address(dto.address())
				.build();
		
	}

}
