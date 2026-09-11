package com.paul.inventory_management.mapper;

import org.springframework.stereotype.Component;

import com.paul.inventory_management.dto.CategoryCreateDTO;
import com.paul.inventory_management.dto.CategoryResponseDTO;
import com.paul.inventory_management.entity.Category;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
	
	public CategoryResponseDTO toDTO(Category category) {
		
		return new CategoryResponseDTO(
				
				category.getId(),
				category.getName(),
				category.getDescription()
				
				);
		
	}
	
	public Category toEntity(CategoryCreateDTO dto) {
		
		return Category.builder()
				.name(dto.name())
				.description(dto.description())
				.build();
		
	}
	
	

}
