
package service;

import org.springframework.stereotype.Service;

import dto.ProductCreateDTO;
import dto.ProductResponseDTO;
import dto.ProductUpdateDTO;
import entity.Category;
import entity.Product;
import enums.Status;
import exception.BusinessRuleException;
import exception.ResourceAlreadyExistsException;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.CategoryMapper;
import mapper.ProductMapper;
import repository.CategoryRepository;
import repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	private final CategoryRepository categoryRepository;
	
	public ProductResponseDTO create(ProductCreateDTO dto) {
		
		if (productRepository.existsBySku(dto.sku())) {
			throw new BusinessRuleException("Already exists a product with SKU " + dto.sku());
		}
		
		Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(() 
				-> new ResourceNotFoundException("Category not found with id " + dto.categoryId()));
		
		Product product = productMapper.toEntity(dto, category);
		
		product.setStatus(Status.ACTIVE);
		
		Product savedProduct = productRepository.save(product);
		
		return productMapper.toDTO(savedProduct);
		
	}
	
	public ProductResponseDTO update(Long id, ProductUpdateDTO dto) {
		
		Product product = productRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Product not found with id " + id));
		
		if (dto.sku() != null) {
			
			if (productRepository.existsBySkuAndIdNot(dto.sku(), id)) {
				throw new ResourceAlreadyExistsException("Product aready exists with sku " + dto.sku());
			}
			
			product.setSku(dto.sku());
			
		}
		
		if (dto.name() != null) {
			
			product.setName(dto.name());
			
		}
		
		if (dto.brand() != null) {
			
			product.setBrand(dto.brand());
			
		}
		
		if (dto.description() != null) {
			
			product.setDescription(dto.description());
			
		}
		
		if (dto.purchasePrice() != null) {
			
			product.setPurchasePrice(dto.purchasePrice());
			
		}
		
		if (dto.salePrice() != null) {
			
			product.setSalePrice(dto.salePrice());
			
		}
		
		if (dto.minimumStock() != product.getMinimumStock()) {
			
			product.setMinimumStock(dto.minimumStock());
						
		}

		if (dto.imgUrl() != null) {
			
			product.setImageUrl(dto.imgUrl());
			
		}
		
		if (dto.categoryId() != null) {
			
			Category category = categoryRepository.findById(dto.categoryId()).orElseThrow(() 
					-> new ResourceNotFoundException("Category not found with id " + dto.categoryId()));
			
			product.setCategory(category);
			
		}
		
		Product savedProduct = productRepository.save(product);
		
		return productMapper.toDTO(savedProduct);
		
	}
	
	public void deactivate(Long id) {
		
		Product product = productRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Product not found with id " + id));
		
		product.setStatus(Status.INACTIVE);
		
		productRepository.save(product);
		
		
	}
	
	public ProductResponseDTO findById(Long id) {
		
		Product product = productRepository.findById(id).orElseThrow(()
				-> new ResourceNotFoundException("Product not found with id " + id));
		
		return productMapper.toDTO(product);
		
	}
	
	public ProductResponseDTO findBySku(String sku) {
		
		Product product = productRepository.findBySku(sku).orElseThrow(()
				-> new ResourceNotFoundException("Product not found with sku " + sku));
		
		return productMapper.toDTO(product);
		
	}
	
}
