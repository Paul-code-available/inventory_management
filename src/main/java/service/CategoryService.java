package service;

import org.springframework.stereotype.Service;

import dto.CategoryRequestDTO;
import dto.CategoryResponseDTO;
import entity.Category;
import enums.Status;
import lombok.RequiredArgsConstructor;
import mapper.CategoryMapper;
import repository.CategoryRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	public CategoryResponseDTO create (CategoryRequestDTO dto) {
		
		if (categoryRepository.existsByNameIgnoreCase(dto.name())) {
			// retornar una excepcion
		}
		
		Category category = categoryMapper.toEntity(dto);
		
		category.setStatus(Status.ACTIVE);
		
		Category savedCategory = categoryRepository.save(category);
		
		return categoryMapper.toDTO(savedCategory);
	
	}
	
}
