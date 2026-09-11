package service;

import org.springframework.stereotype.Service;

import dto.ProductResponseDTO;
import dto.SupplierCreateDTO;
import dto.SupplierResponseDTO;
import dto.SupplierUpdateDTO;
import entity.Supplier;
import enums.Status;
import exception.BusinessRuleException;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.SupplierMapper;
import repository.ProductRepository;
import repository.SupplierRepository;

@Service
@RequiredArgsConstructor
public class SupplierService {

	private final SupplierRepository supplierRepositoy;
	private final SupplierMapper supplierMapper;
	
	public SupplierResponseDTO create(SupplierCreateDTO dto) {
		
		if (supplierRepositoy.existsByEmail(dto.email())) {
			throw new BusinessRuleException("Email already exists");	
		}
		
		if (supplierRepositoy.existByTaxId(dto.taxId())) {
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
			
			if (supplierRepositoy.existByTaxIdAndIdNot(dto.taxId(), id)) {
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
