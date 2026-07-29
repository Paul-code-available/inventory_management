package service;

import org.springframework.stereotype.Service;

import dto.CategoryCreateDTO;
import dto.CategoryResponseDTO;
import dto.CategoryUpdateDTO;
import entity.Category;
import enums.Status;
import exception.BusinessRuleException;
import exception.ResourceAlreadyExistsException;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.CategoryMapper;
import repository.CategoryRepository;

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





