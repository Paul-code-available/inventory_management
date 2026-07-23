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

	public CategoryResponseDTO findById(Long id) throws Exception {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Categoria no encontrada"));
		
		return categoryMapper.toDTO(category);
			
	}
	
	public void delete(Long id) throws Exception {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Categoria no encontrada"));
		
		if (category.getProducts().stream().anyMatch(product -> product.getStatus() == Status.ACTIVE)) {
			// retornar una expcepcion 
		}

		category.setStatus(Status.INACTIVE);
		
		categoryRepository.save(category);
		
	}
	
	public CategoryResponseDTO update(Long id, CategoryRequestDTO dto) throws Exception {
		
		Category category = categoryRepository.findById(id).orElseThrow(() -> new Exception("Categoria no encontrada"));

		if (dto.name() != null ) {
			
			if (categoryRepository.existsByNameIgnoreCaseAndIdNot(dto.name(), id)) {
				// excepcion 
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





