package service;

import java.util.List;

import org.springframework.stereotype.Service;

import dto.ProductResponseDTO;
import dto.ProductSupplierCreateDTO;
import dto.ProductSupplierResponseDTO;
import dto.ProductSupplierUpdateDTO;
import entity.Product;
import entity.ProductSupplier;
import entity.Supplier;
import exception.BusinessRuleException;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.ProductSupplierMapper;
import repository.ProductRepository;
import repository.ProductSupplierRepository;
import repository.SupplierRepository;

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
			productSupplier.setSupplierPrice(dto.supplierSku());
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


