package com.paul.inventory_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.paul.inventory_management.dto.ProductResponseDTO;
import com.paul.inventory_management.dto.ProductSupplierCreateDTO;
import com.paul.inventory_management.dto.ProductSupplierResponseDTO;
import com.paul.inventory_management.dto.ProductSupplierUpdateDTO;
import com.paul.inventory_management.entity.Product;
import com.paul.inventory_management.entity.ProductSupplier;
import com.paul.inventory_management.entity.Supplier;
import com.paul.inventory_management.exception.BusinessRuleException;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.mapper.ProductSupplierMapper;
import com.paul.inventory_management.repository.ProductRepository;
import com.paul.inventory_management.repository.ProductSupplierRepository;
import com.paul.inventory_management.repository.SupplierRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSupplierService {

	private final ProductSupplierRepository productSupplierRepository;
	private final ProductSupplierMapper productSupplierMapper;
	
	private final ProductRepository productRepository;
	private final SupplierRepository supplierRepository;
	
	public ProductSupplierResponseDTO create(ProductSupplierCreateDTO dto) {
		
		Product product = productRepository.findById(dto.productId()).orElseThrow(()
				-> new ResourceNotFoundException("Product not found with id " + dto.productId()));
		
		Supplier supplier = supplierRepository.findById(dto.supplierId()).orElseThrow(()
				-> new ResourceNotFoundException("Supplier not found with id " + dto.supplierId()));
		
		if (productSupplierRepository.existsByProductIdAndSupplierId(dto.productId(), dto.supplierId())) {
			throw new BusinessRuleException("This product is already associated with this supplier");
		}
		
		ProductSupplier productSupplier = productSupplierMapper.toEntity(dto, product, supplier);
		
		ProductSupplier savedProductSupplier = productSupplierRepository.save(productSupplier);
		
		return productSupplierMapper.toDTO(savedProductSupplier);
		
	}
	
	public ProductSupplierResponseDTO findById(Long id) {
		
		ProductSupplier productSupplier = productSupplierRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Product Supplier not found with id " + id));
		
		return productSupplierMapper.toDTO(productSupplier);
		
	}
	
	public List<ProductSupplierResponseDTO> findAll() {
		
		return productSupplierRepository.findAll()
				.stream()
				.map(productSupplierMapper::toDTO)
				.toList();
		
	}
	
	public List<ProductSupplierResponseDTO> findByProduct(Long productId) {
		
		productRepository.findById(productId).orElseThrow(()
				-> new ResourceNotFoundException("Product not found with id " + productId));
		
		return productSupplierRepository.findByProductId(productId)
				.stream()
				.map(productSupplierMapper::toDTO)
				.toList();
		
	}
	
	public List<ProductSupplierResponseDTO> findBySupplier(Long supplierId) {
		
		supplierRepository.findById(supplierId).orElseThrow(()
				-> new ResourceNotFoundException("Supplier not found with id " + supplierId));
		
		return productSupplierRepository.findBySupplierId(supplierId)
				.stream()
				.map(productSupplierMapper::toDTO)
				.toList();
		
	}
	
	public ProductSupplierResponseDTO update(Long id, ProductSupplierUpdateDTO dto) {
		
		ProductSupplier productSupplier = productSupplierRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Product supplier not found with id " + id));
		
		if (dto.supplierPrice() != null) {
			productSupplier.setSupplierPrice(dto.supplierPrice());
		}
		
		if (dto.supplierSku() != null) {
			productSupplier.setSupplierSku(dto.supplierSku());
		}
		
		if (dto.leadTimeDays() != null) {
			productSupplier.setLeadTimeDays(dto.leadTimeDays());
		}
		
		ProductSupplier savedProductSupplier = productSupplierRepository.save(productSupplier);
		
		return productSupplierMapper.toDTO(savedProductSupplier);
		
	}
	
	public void deactivate(Long id) {
		
		ProductSupplier productSupplier = productSupplierRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Product supplier not found with id " + id));
		
		productSupplierRepository.delete(productSupplier);
		
	}
	
	
}


