package mapper;

import org.springframework.stereotype.Component;

import dto.CategoryRequestDTO;
import dto.CategoryResponseDTO;
import entity.Category;

@Component
public class CategoryMapper {
	
	public CategoryResponseDTO toDTO(Category category) {
		
		return new CategoryResponseDTO(
				
				category.getId(),
				category.getName(),
				category.getDescription()
				
				);
		
	}
	
	public Category toEntity(CategoryRequestDTO dto) {
		
		return Category.builder()
				.name(dto.name())
				.description(dto.description())
				.build();
		
	}
	
	

}
