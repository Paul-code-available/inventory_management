package com.paul.inventory_management.service;

import org.springframework.stereotype.Service;

import com.paul.inventory_management.dto.CategoryCreateDTO;
import com.paul.inventory_management.dto.CategoryResponseDTO;
import com.paul.inventory_management.dto.CategoryUpdateDTO;
import com.paul.inventory_management.entity.Category;
import com.paul.inventory_management.enums.Status;
import com.paul.inventory_management.exception.BusinessRuleException;
import com.paul.inventory_management.exception.ResourceAlreadyExistsException;
import com.paul.inventory_management.exception.ResourceNotFoundException;
import com.paul.inventory_management.mapper.CategoryMapper;
import com.paul.inventory_management.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public CategoryResponseDTO create (CategoryCreateDTO dto) {
		
		if (categoryRepository.existsByNameIgnoreCase(dto.name())) {
			throw new ResourceAlreadyExistsException("Category already exists with name " + dto.name());
		}
		
		Category category = categoryMapper.toEntity(dto);
		
		category.setStatus(Status.ACTIVE);
		
		Category savedCategory = categoryRepository.save(category);
		
		return categoryMapper.toDTO(savedCategory);
	
	}

	public CategoryResponseDTO findById(Long id) {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
		
		return categoryMapper.toDTO(category);
			
	}
	
	public void delete(Long id) {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));
		
		if (category.getProducts().stream().anyMatch(product -> product.getStatus() == Status.ACTIVE)) {
			throw new BusinessRuleException("Cannot delete categories with active products");
		}

		category.setStatus(Status.INACTIVE);
		
		categoryRepository.save(category);
		
	}
	
	public CategoryResponseDTO update(Long id, CategoryUpdateDTO dto) {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + id));

		if (dto.name() != null ) {
			
			if (categoryRepository.existsByNameIgnoreCaseAndIdNot(dto.name(), id)) {
				throw new ResourceAlreadyExistsException("Category already exists with name " + dto.name());
			}
			
			category.setName(dto.name());
		}
		
		if (dto.description() != null ) {
			category.setDescription(dto.description());
		}

		Category updatedCategory = categoryRepository.save(category);
		
		return categoryMapper.toDTO(updatedCategory);
		
	}
	
}





